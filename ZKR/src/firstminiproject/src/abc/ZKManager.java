package abc;

import java.util.List;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
public interface ZKManager {

	//create a ZNode
	public void create(String path, byte[] data) throws KeeperException,
	InterruptedException;
	
	// get Znode Stats
	public Stat getZNodeStats(String path) throws KeeperException,
	InterruptedException;
	
	
	//get Znode data
	public Object getZNodeData(String path, boolean watchFlag) throws KeeperException,
	InterruptedException;
	
	//updateZnode Data
	public void update(String path, byte[] data) throws KeeperException,
	InterruptedException;
	
	// get a zNode children
	public List<String> getZNodeChildren(String path) throws KeeperException,
	InterruptedException;
	
	// Delete a zNode
	
	public void delete(String path) throws KeeperException,
	InterruptedException;
}
