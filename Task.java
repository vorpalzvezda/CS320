package task;


/**
 * Defines the Task Class object with attribute validation
 * 
 * Constraints:
 * - taskID: required, maximum length 10, immutable
 * - taskname: required, maximum length 20
 * - taskdescription: required, maximum length 50
 */
public class Task {
	
	// Attribute declarations
	private final String taskID;
	private String taskname;
	private String taskdescription;
	
	/**
	 * Task Constructor with validation 
	 * 
	 * @param id
	 * @param name
	 * @param info
	 */
	public Task(String id, String name, String info){
		
		if(id == null || id.length() > 10 || id.length() == 0) {
			throw new IllegalArgumentException("Invalid Task ID");
		}
		this.taskID = id;
		setTaskName(name);
		setDescription(info);
	}
	
	// Sets/Updates name attribute value 
	public void setTaskName(String name) {
		if (name == null || name.length() > 20 || name.length() == 0) {
			throw new IllegalArgumentException("Invalid Task Name");
		}
		this.taskname = name;
	}
	
	// Sets/Updates description attribute value
	public void setDescription(String info) {
		if (info == null || info.length() > 50 || info.length() == 0) {
			throw new IllegalArgumentException("Invalid Task Description");
		}
		this.taskdescription = info;
	}
	
	/*
	 * GETTER Methods: Returns attribute values
	 */
	public String getID() {
		return this.taskID;
	}
	
	public String getName() {
		return this.taskname;
	}
	
	public String getDescription() {
		return this.taskdescription;
	}

}
