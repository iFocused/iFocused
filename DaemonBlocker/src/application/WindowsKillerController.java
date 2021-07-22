package application;

import java.io.IOException;
import java.util.TimerTask;

import application.entities.Process;
import application.usecases.BlockListRepository;

class WindowsKillerController extends TimerTask implements KillerStrategy {
	private BlockListRepository blockListRepository;

	public WindowsKillerController(BlockListRepository blockListRepository) {
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

		try {
			for (int k : blockListRepository.getBlockLists().keySet()) {
				for (Process process : blockListRepository.getBlockLists().get(k).getBlockedProcesses()) {
					rt.exec(String.format("taskkill /im %s.exe /f", process.getProcessName()));
					System.out.println(process.getProcessName());
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
