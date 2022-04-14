/**
 * 
 */
package by.petrovich.storage.dao.impl;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import by.petrovich.storage.entity.CncMachine;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.entity.Detail;

/**
 * @author 42931
 *
 */
public class CncProgramDaoImplTest {
	private static final int CNC_PROGRAM_ID = 154;
	private static final String CNC_PROGRAM_NUMBER = "150_79302067";
	private static final int CNC_PROGRAM_OPERATION_NUMBER = 030;
	private static final String CNC_PROGRAM_TEXT = "%\n" + "N001G00G99M73\n" + "N131G50X87000Z34000S22T02\n"
			+ "N132G00X0Z20000H02M03\n" + "N133Z15500M08\n" + "N134G01Z12490F30\n" + "N135G00Z20000M09\n"
			+ "N136X87000Z34000H00M05\n" + "N002G50X81000Z45000T01S22\n" + "N003G00X33930Z18000H01M04\n"
			+ "N004Z12000M08\n" + "N005G01Z9250F30\n" + "N006G00X34100Z11000\n" + "N007G01X33300Z11400\n"
			+ "N008X28650\n" + "N009G00X28800Z14200\n" + "N010G01X28050" + "N206X68000Z44000H00M05C30000\n"
			+ "N207M63\n" + "N208M06\n" + "N209M73\n" + "N210G00X90000Z49000\n" + "N204M30\n" + "%";
	private static final String CNC_PROGRAM_COMMENT = "add drill D=8,5";
	private static final boolean IS_ACTIVE = true;
	private static final int LOGIN_PERSONNEL_NUMBER = 42655;
	private static Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	private static final Timestamp CNC_PROGRAM_CREATION_DATE = timestamp;

	private static final int DETAIL_ID = 565454;
	private static final String DETAIL_NAME = "7555-1712212-10";

	private static final int CNC_MACHINE_ID = 18675;
	private static final String CNC_MACHINE_MODEL = "NBH-6";
	private static final int CODE_EQUIPMENT = 115;

	@Mock
	private CncProgramDaoImpl cncProgramDaoImplMock;
	private CncProgram expectedCncProgram;
	private CncProgram actualCncProgram;
	private List<CncProgram> expectedCncPrograms;
	private CncProgram firstCncProgram;
	private CncProgram secondCncProgram;
	private CncProgram toUpdateCncProgram;
	private CncProgram expectedUpdatedCncProgram;
	private Detail detail;
	private CncMachine cncMachine;

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		expectedCncProgram = new CncProgram(54, "7548-1712056", 030, "%\r\n" + "N001G99M73\r\n"
				+ "N002G50X81000Z42000T05S31\r\n" + "N003G00X50000Z3750H05M08\r\n" + "N004X30800M04\r\n"
				+ "N005G01X23800F35\r\n" + "N006G00X28500Z3950\r\n" + "N007G01X29390Z3505\r\n" + "N008Z-200\r\n"
				+ "N009G00X40000Z10000M09\r\n" + "N010X81000Z42000H00M05\r\n" + "N011G50X94000Z35000T08S31\r\n"
				+ "N012G00X23500Z15000H08M08\r\n" + "N013Z3900M04\r\n" + "N014G01X24805F100\r\n" + "N015Z2300F30\r\n"
				+ "N016G00X24000Z3850\r\n" + "N017G01X25495F100\r\n" + "N018X24700Z3453F30\r\n" + "N019G00Z15000M09\r\n"
				+ "N020X94000Z35000H00M05\r\n" + "N021M30\r\n" + "%", timestamp, "пересдал", true, detail, cncMachine,
				42900);

	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.CncProgramDaoImpl#readBatch(int, int)}.
	 */
	@Test
	public void testReadBatch() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.CncProgramDaoImpl#readBatchByLoginPersonnelNumber(int)}.
	 */
	@Test
	public void testReadBatchByLoginPersonnelNumber() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.CncProgramDaoImpl#readBatchByDate()}.
	 */
	@Test
	public void testReadBatchByDate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.CncProgramDaoImpl#getNumberOfRecords()}.
	 */
	@Test
	public void testGetNumberOfRecords() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.CncProgramDaoImpl#create(by.petrovich.storage.entity.CncProgram)}.
	 */
	@Test
	public void testCreate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.CncProgramDaoImpl#createDetail(by.petrovich.storage.entity.Detail)}.
	 */
	@Test
	public void testCreateDetail() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.CncProgramDaoImpl#createCncMachine(by.petrovich.storage.entity.CncMachine)}.
	 */
	@Test
	public void testCreateCncMachine() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.CncProgramDaoImpl#read(int)}.
	 */
	@Test
	public void testRead() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.CncProgramDaoImpl#update(by.petrovich.storage.entity.CncProgram, int)}.
	 */
	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.CncProgramDaoImpl#readBatchByProgramName(java.lang.String)}.
	 */
	@Test
	public void testReadBatchByProgramName() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.CncProgramDaoImpl#findAmountOfRows(int, int)}.
	 */
	@Test
	public void testFindAmountOfRows() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.CncProgramDaoImpl#readDetailById(int)}.
	 */
	@Test
	public void testReadDetailById() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.CncProgramDaoImpl#readDetailByName(java.lang.String)}.
	 */
	@Test
	public void testReadDetailByName() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.CncProgramDaoImpl#readCncMachineByModel(java.lang.String)}.
	 */
	@Test
	public void testReadCncMachineByModel() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.CncProgramDaoImpl#readCncMachine()}.
	 */
	@Test
	public void testReadCncMachine() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.CncProgramDaoImpl#readDetail()}.
	 */
	@Test
	public void testReadDetail() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.CncProgramDaoImpl#updateDetail(by.petrovich.storage.entity.Detail, int)}.
	 */
	@Test
	public void testUpdateDetail() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.CncProgramDaoImpl#updateCncMachine(by.petrovich.storage.entity.CncMachine, int)}.
	 */
	@Test
	public void testUpdateCncMachine() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.CncProgramDaoImpl#readCncMachineById(int)}.
	 */
	@Test
	public void testReadCncMachineById() {
		fail("Not yet implemented");
	}

}
