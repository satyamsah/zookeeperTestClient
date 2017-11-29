package abc;


import org.junit.*;
import abc.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;

public class Test {
	
	public static void main(String args[]) throws KeeperException, InterruptedException{
		ZKClientManagerImpl zkmanager = new ZKClientManagerImpl();
		String path = "Kumar-ZNode";
		byte[] data = "www.google.com".getBytes();
		Test.testCreate(zkmanager,path,data);
		Test.testGetZNodeStats(zkmanager, path, data);
		Test.testGetZNodeData(zkmanager, path, data);
		Test.testUpdate(zkmanager,  path, data);
		Test.getZNodeChildren( zkmanager,  path, data);
		Test.testDelete( zkmanager,  path,data);
		
		
	}


	public static void testCreate(ZKClientManagerImpl zkmanager, String path,byte[] data) throws KeeperException, InterruptedException {

		zkmanager.create(path, data);
		Stat stat = zkmanager.getZNodeStats(path);
		assertNotNull(stat);
		zkmanager.delete(path);
	}


	public static void testGetZNodeStats(ZKClientManagerImpl zkmanager, String path,byte[] data) throws KeeperException, InterruptedException {

		zkmanager.create(path, data);
		Stat stat = zkmanager.getZNodeStats(path);
		assertNotNull(stat);
		assertNotNull(stat.getAversion());
		zkmanager.delete(path);
	}

	// updateZnode Data
	

	public static void testGetZNodeData(ZKClientManagerImpl zkmanager, String path,byte[] data) throws KeeperException, InterruptedException {
		zkmanager.create(path, data);
		String data1 = (String)zkmanager.getZNodeData(path,false);
		assertNotNull(data1);
		zkmanager.delete(path);
	}
	
	

	public static void testUpdate(ZKClientManagerImpl zkmanager, String path,byte[] data) throws KeeperException, InterruptedException {
		zkmanager.create(path, data);
		String data1 = "dsfsdfs";
		byte[] dataBytes = data1.getBytes();

		zkmanager.update(data1, dataBytes);
		String retrivedData = (String) zkmanager.getZNodeData(path, false);
		assertNotNull(retrivedData);
		zkmanager.delete(path);

	}

	public static void  getZNodeChildren(ZKClientManagerImpl zkmanager, String path,byte[] data) throws KeeperException,
	InterruptedException{
		zkmanager.create(path, data);
		List<String> children =zkmanager.getZNodeChildren(path);
		assertNotNull(children);
		zkmanager.delete(path);
	}
	
	public static void testDelete(ZKClientManagerImpl zkmanager, String path,byte[] data) throws KeeperException,InterruptedException{
		zkmanager.create(path, data);
		zkmanager.delete(path);
		Stat stat= zkmanager.getZNodeStats(path);
		assertNull(stat);
	}
	

}
