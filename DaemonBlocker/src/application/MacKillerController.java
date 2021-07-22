package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TimerTask;

import application.entities.Process;
import application.usecases.BlockListRepository;

class MacKillerController extends TimerTask implements KillerStrategy {
	private BlockListRepository blockListRepository;

	public MacKillerController(BlockListRepository blockListRepository) {
		this.blockListRepository = blockListRepository;
	}
	
	public void update(BlockListRepository blockListRepository) {
		this.cancel();
		this.blockListRepository = blockListRepository;
		this.run();
	}

	public void run() {
		System.out.println("1 min passed");
		Runtime rt = Runtime.getRuntime();
		System.out.println(blockListRepository.getBlockLists().keySet());
		
		try {
			for (int k : blockListRepository.getBlockLists().keySet()) {
				
				for (Process process : blockListRepository.getBlockLists().get(k).getBlockedProcesses()) {
					String command = String.format("ps aux | grep -v grep |grep -i " + "\"%s\" | awk '{print $2;}'", process.getProcessName());
					String[] commands = {"/bin/sh", "-c", command};

					java.lang.Process proc = rt.exec(commands);
					BufferedReader stdInput = new BufferedReader(new
			                InputStreamReader(proc.getInputStream()));
					
					ArrayList<Integer> ids = new ArrayList<>();
					
					String s = null;
			        while ((s = stdInput.readLine()) != null) {
			            System.out.println( Integer.parseInt(s) );
			            ids.add(Integer.parseInt(s));
			        }
			        
			        for (Integer id : ids) {
		                rt.exec(String.format("kill -SIGKILL %d", id));
		            }
					
					
					System.out.println(process.getProcessName());
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
