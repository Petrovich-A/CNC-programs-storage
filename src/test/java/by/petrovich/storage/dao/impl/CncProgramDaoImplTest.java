/**
 * 
 */
package by.petrovich.storage.dao.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import by.petrovich.storage.dao.DaoException;
import by.petrovich.storage.entity.CncMachine;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.entity.Detail;

/**
 * @author Petrovich A.V.
 *
 */
public class CncProgramDaoImplTest {
	private static final int OFFSET = 10;
	private static final int NUMBER_OF_RECORDS = 7;
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	private static final int PERSONNEL_NUMBER = 42105;

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
	private int expectedNumberOfRecords;
	private int firstNumberOfRecords;
	private int secondNumberOfRecords;

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
		firstCncProgram = new CncProgram(54, "7548-1712056", 030, "%\r\n" + "N001G99M73\r\n"
				+ "N002G50X81000Z42000T05S31\r\n" + "N003G00X50000Z3750H05M08\r\n" + "N004X30800M04\r\n"
				+ "N005G01X23800F35\r\n" + "N006G00X28500Z3950\r\n" + "N007G01X29390Z3505\r\n" + "N008Z-200\r\n"
				+ "N009G00X40000Z10000M09\r\n" + "N010X81000Z42000H00M05\r\n" + "N011G50X94000Z35000T08S31\r\n"
				+ "N012G00X23500Z15000H08M08\r\n" + "N013Z3900M04\r\n" + "N014G01X24805F100\r\n" + "N015Z2300F30\r\n"
				+ "N016G00X24000Z3850\r\n" + "N017G01X25495F100\r\n" + "N018X24700Z3453F30\r\n" + "N019G00Z15000M09\r\n"
				+ "N020X94000Z35000H00M05\r\n" + "N021M30\r\n" + "%", timestamp, "пересдал", true, detail, cncMachine,
				42900);
		secondCncProgram = new CncProgram(154, "7519-3103089-20", 050,
				"%\r\n" + "N001G50X90000Z48000T01S11\r\n" + "N002G00X41700Z20000H01M08\r\n" + "N003G99Z5200M04\r\n"
						+ "N004G01Z1200F35\r\n" + "N005G00X42000Z4650 \r\n" + "N006G01X37500\r\n"
						+ "N007G00X41800Z4800 \r\n" + "N008G01Z4430F200 \r\n" + "N009X37500F35\r\n"
						+ "N010G00X41800Z4500 \r\n" + "N011G01Z4220F200 \r\n" + "N012X37500F35\r\n"
						+ "N013G00X41060Z4300",
				timestamp, "поменял режимы рез.", true, detail, cncMachine, 31457);
		expectedCncPrograms = List.of(firstCncProgram, secondCncProgram);
		expectedNumberOfRecords = 7;
		firstNumberOfRecords = 7;
		secondNumberOfRecords = 15;

	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.CncProgramDaoImpl#readBatch(int, int)}.
	 */
	@Test
	public void testReadBatch() throws DaoException {
		when(cncProgramDaoImplMock.readBatch(OFFSET, NUMBER_OF_RECORDS)).thenReturn(expectedCncPrograms);
		List<CncProgram> actual = cncProgramDaoImplMock.readBatch(OFFSET, NUMBER_OF_RECORDS);
		assertThat(actual).containsExactly(firstCncProgram, secondCncProgram);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.CncProgramDaoImpl#readBatchByPersonnelNumber(int)}.
	 */
	@Test
	public void testReadBatchByPersonnelNumber() throws DaoException {
		when(cncProgramDaoImplMock.readBatchByPersonnelNumber(PERSONNEL_NUMBER))
				.thenReturn(expectedCncPrograms);
		List<CncProgram> actual = cncProgramDaoImplMock.readBatchByPersonnelNumber(PERSONNEL_NUMBER);
		assertThat(actual).containsExactly(firstCncProgram, secondCncProgram);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.CncProgramDaoImpl#readBatchByDate()}.
	 */
	@Test
	public void testReadBatchByDate() throws DaoException {
		when(cncProgramDaoImplMock.readBatchByDate()).thenReturn(expectedCncPrograms);
		List<CncProgram> actual = cncProgramDaoImplMock.readBatchByDate();
		assertThat(actual).containsExactly(firstCncProgram, secondCncProgram);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.CncProgramDaoImpl#receiveNumberOfRecords()}.
	 */
	@Test
	public void testGetNumberOfRecords() throws DaoException {
		when(cncProgramDaoImplMock.receiveNumberOfRecords()).thenReturn(expectedNumberOfRecords);
		int actual = cncProgramDaoImplMock.receiveNumberOfRecords();
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.CncProgramDaoImpl#createCncProgram(by.petrovich.storage.entity.CncProgram)}.
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
	 * {@link by.petrovich.storage.dao.impl.CncProgramDaoImpl#readCncProgramById(int)}.
	 */
	@Test
	public void testRead() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.CncProgramDaoImpl#updateCncProgram(by.petrovich.storage.entity.CncProgram, int)}.
	 */
	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.CncProgramDaoImpl#readCncProgramByName(java.lang.String)}.
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
	 * {@link by.petrovich.storage.dao.impl.CncProgramDaoImpl#readAllCncMachines()}.
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
