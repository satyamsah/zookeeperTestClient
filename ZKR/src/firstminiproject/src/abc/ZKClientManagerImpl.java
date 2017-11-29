package abc;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.omg.CORBA.INITIALIZE;

import java.util.*;

public class ZKClientManagerImpl implements ZKManager {

	private static ZooKeeper zkeeper;
	private static ZKConnection zkConnection;

	public ZKClientManagerImpl() {
		initialize();
	}

	private void initialize() {
		try {
			zkConnection = new ZKConnection();
			zkeeper = zkConnection.connect("localhost");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void closeConnection() {
		try {
			zkConnection.close();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void create(String path, byte[] data) throws KeeperException, InterruptedException {
		// TODO Auto-generated method stub
		zkeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

	}

	@Override
	public Stat getZNodeStats(String path) throws KeeperException, InterruptedException {
		// TODO Auto-generated method stub
		Stat stat = zkeeper.exists(path, true);
		if (stat != null) {
			System.out.println("Node exists and node version is" + stat.getVersion());
		} else {
			System.out.println("Node does not exists");
		}
		return stat;
	}

	@Override
	public Object getZNodeData(String path, boolean watchFlag) throws KeeperException, InterruptedException {
		// TODO Auto-generated method stub
		try {
			Stat stat = getZNodeStats(path);
			byte[] b = null;
			if (stat != null) {
				if (watchFlag) {
					ZKWatcher watch = new ZKWatcher();
					b = zkeeper.getData(path, null, null);
				} else {
					b = zkeeper.getData(path, null, null);
				}

				String data = new String(b, "UTF-8");
				System.out.println(data);
				return data;
			} else {
				System.out.println("No nodes exists");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

	@Override
	public void update(String path, byte[] data) throws KeeperException, InterruptedException {
		// TODO Auto-generated method stub
		int version=zkeeper.exists(path, true).getAversion();
		zkeeper.setData(path, data, version);

	}

	@Override
	public List<String> getZNodeChildren(String path) throws KeeperException, InterruptedException {
		// TODO Auto-generated method stub
		
		Stat stat= getZNodeStats(path);
		List<String> children=null;
		if(stat!=null) {
			children=zkeeper.getChildren(path, false);
			for(int i=0;i<children.size();i++) {
				System.out.println(children.get(i));
			}
		}else {
			System.out.println("Node does not exist");
		}
		return children;
	}

	@Override
	public void delete(String path) throws KeeperException, InterruptedException {
		// TODO Auto-generated method stub
		
		int version=zkeeper.exists(path, true).getAversion();
		zkeeper.delete(path, version);

	}
	
	public static void main(String [] args) {
		
	}

}
