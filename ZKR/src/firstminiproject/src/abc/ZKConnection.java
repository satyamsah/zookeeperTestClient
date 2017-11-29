package abc;
import java.io.*;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.KeeperState;
public class ZKConnection {
		
		private ZooKeeper zoo;
		final CountDownLatch connectionLatch= new CountDownLatch(1);
		public ZKConnection() {
			
		}
		
		public ZooKeeper connect(String host) throws IOException,
		InterruptedException{
			zoo=new ZooKeeper(host, 2000, new Watcher() {
				public void process(WatchedEvent we) {
					if(we.getState()== KeeperState.SyncConnected) {
						connectionLatch.countDown();
					}
				}
			});
			
		connectionLatch.await();
		return zoo;
		}
		
		public void close() throws InterruptedException{
			zoo.close();
		}
	

}
