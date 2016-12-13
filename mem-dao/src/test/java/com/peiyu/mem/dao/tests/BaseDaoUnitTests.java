package com.peiyu.mem.dao.tests;

import org.apache.commons.collections.CollectionUtils;
import org.dbunit.DatabaseTestCase;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 * 测试大概流程，建立数据库连接-> 备份表 -> 调用Dao层接口 ->
 * 从数据库取实际结果-> 事先准备的期望结果 -> 断言 -> 回滚数据库 -> 关闭数据库
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/config/spring-dao-test.xml"})
@Rollback
public class BaseDaoUnitTests extends DatabaseTestCase {
    private String fileName;
    private Connection connection;
    @Before
    public void setUp() throws Exception{
        dataBackUp();
        super.setUp();
    }
    @Override
    protected IDatabaseConnection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/peiyu_mem?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true", "root", "123456");
        return new MySqlConnection(conn, "peiyu_mem");
    }
    @Override
    protected IDataSet getDataSet() throws Exception {
        InputStream inputStream=getClass().getResourceAsStream("/xmls/backupdata.xml");
        return new FlatXmlDataSetBuilder()
                .setColumnSensing(true)
                .build(inputStream);
    }

    /**
     * 对数据库进行备份
     */
    protected void dataBackUp() throws Exception {
        QueryDataSet dataSet=new QueryDataSet(getConnection());
        List<String> tableNames=getTableNames();
        if (CollectionUtils.isNotEmpty(tableNames)){
            for (String tableName:tableNames){
                dataSet.addTable(tableName);
            }
        }
        String path=this.getClass().getClassLoader().getResource("backup").getPath().toString();
        File file=File.createTempFile("mybackupdata", ".xml",new File(path));
        FileOutputStream fm=new FileOutputStream(file);
        fileName=file.getName();
        FlatXmlDataSet.write(dataSet, fm);
        fm.close();
    }

    /**
     * 添加数据库所有的表名（后期考虑写进配置文件进行读取）
     */
    protected List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();
        tableNames.add("activity");
        tableNames.add("actsubgroup");
        tableNames.add("applylimit");
        tableNames.add("coupon");
        tableNames.add("makingtask");
        tableNames.add("uselimit");
        tableNames.add("member");
        return tableNames;
    }
    /**
     * 关闭数据库
     * @throws SQLException
     */
    protected void closeConnection() throws SQLException
    {
        if(connection!=null)
        {
            connection.close();
            connection=null;
        }
    }
    /**
     * 对数据库进行还原
     * @throws DataSetException
     * @throws DatabaseUnitException
     * @throws SQLException
     * @throws Exception
     */
    protected void dataReduction() throws Exception {
        InputSource xmlSource = new InputSource();
        InputStream is = getClass().getResourceAsStream("/backup/" + fileName);
        xmlSource.setByteStream(is);
        // 解析一个文件，产生一个数据集
        FlatXmlProducer flatXmlProducer = new FlatXmlProducer(xmlSource);
        flatXmlProducer.setColumnSensing(true);
        //执行数据操作
        DatabaseOperation.CLEAN_INSERT.execute(getConnection(), new FlatXmlDataSet(
                flatXmlProducer));
    }
    /**
     * 删除备份的临时文件
     */
    public void deleteBackFile()
    {
        String path=getClass().getClassLoader().getResource("backup").getFile();
        path=path+"/"+fileName;
        File file=new File(path);
        try {
            if(file.isFile()&&file.exists())
            {
                if(!file.delete())
                {
                    System.out.println("删除失败");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        dataReduction();
        deleteBackFile();
        closeConnection();
    }
    /**
     * 数据库连接
     * @param connection
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
