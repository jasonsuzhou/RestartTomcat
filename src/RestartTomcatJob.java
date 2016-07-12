import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class RestartTomcatJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) {
		if (FileListener.needRestartTomcat()) {
			try {
				System.out.println("**********Begin stop tomcat**********");
				Runtime.getRuntime().exec("/yjdata/www/tomcat.sh stop");
				System.out.println("**********Begin sleep 2 mins to wait tomcat shutdown**********");
				Thread.sleep(2*60*1000);
				System.out.println("**********End sleep 2 mins to wait tomcat shutdown**********");
				System.out.println("**********End stop tomcat**********");
				System.out.println("**********Begin start tomcat**********");
				Runtime.getRuntime().exec("/yjdata/www/tomcat.sh start");
				System.out.println("**********End start tomcat**********");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ERROR::" + e.getMessage());
			}
		}
	}

}
