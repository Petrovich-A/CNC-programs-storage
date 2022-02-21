package by.petrovich.storage.dao;

public final class ColumnName {
	/*
	 * users
	 */
	public static final String LOGIN_PERSONNEL_NUMBER = "login_personnel_number";
	public static final String PASSWORD = "password";
	public static final String EMPLOYEE_NAME = "employee_name";
	public static final String EMPLOYEE_SURNAME = "employee_surname";
	public static final String EMPLOYEE_PATRONYMIC = "employee_patronymic";
	public static final String EMAIL = "email";
	public static final String CREATE_TIME = "create_time";
//    public static final String ROLE_ID = "role_id";
//    public static final String POSITION_ID = "position_id";
	/*
	 * user_roles
	 */
	public static final String ROLE_ID = "role_id";
	public static final String ROLE_NAME = "role_name";
	/*
	 * employee_positions
	 */
	public static final String POSITION_ID = "position_id";
	public static final String POSITION_NAME = "position_name";
	/*
	 * cnc_programs
	 */
	public static final String PROGRAM_ID = "program_id";
	public static final String PROGRAM_NAME = "program_name";
	public static final String OPERATION_NUMBER = "operation_number";
	public static final String PROGRAM_TEXT = "program_text";
	public static final String create_time = "create_time";
	public static final String COMMENT = "comment";
	public static final String ACTIVE = "active";
//    public static final String LOGIN_PERSONNEL_NUMBER = "login_personnel_number";
//    public static final String DETAIL_ID = "detail_id";
//    public static final String CNC_MACHINE_ID = "cnc_machine_id";
	/*
	 * cnc_machines
	 */
	public static final String CNC_MACHINE_ID = "cnc_machine_id";
	public static final String MODEL = "model";
	public static final String CODE_EQUIPMENT = "code_equipment";
	/*
	 * details
	 */
	public static final String DETAIL_ID = "detail_id";
	public static final String DETAIL_NAME = "detail_name";

	private ColumnName() {
	}
}
