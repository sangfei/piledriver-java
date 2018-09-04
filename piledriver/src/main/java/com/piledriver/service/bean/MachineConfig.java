package com.piledriver.service.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_config")
public class MachineConfig {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "machineid", nullable = false, length = 11)
	private int machineId;
	@Id
	@Column(name = "machinename", nullable = false, length = 64)
	private String machineName;

	@Column(name = "delayseconds", nullable = false, length = 11)
	private int delay;

	@Column(name = "durationseconds", nullable = false, length = 11)
	private int duration;

	public MachineConfig() {
	}

	public int getMachineId() {
		return machineId;
	}

	public void setMachineId(int machineId) {
		this.machineId = machineId;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "MachineConfig [machineId=" + machineId + ", machineName=" + machineName + ", delay=" + delay
				+ ", duration=" + duration + "]";
	}

}