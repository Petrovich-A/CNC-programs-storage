package by.petrovich.storage.validator.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import by.petrovich.storage.entity.CncMachine;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.entity.Detail;

public class CncProgramValidatorTest {
	CncProgramValidator cncProgramValidator = CncProgramValidator.getInstance();

	@Test
	public void isNumberValid() {
		String number = "150_79302067";
		String number1 = "150-79302067";
		String number2 = "79302067";
		boolean result = cncProgramValidator.isNumberValid(number);
		boolean result1 = cncProgramValidator.isNumberValid(number1);
		boolean result2 = cncProgramValidator.isNumberValid(number2);
		assertTrue(result);
		assertTrue(result1);
		assertTrue(result2);
	}

	@Test
	public void isOperationNumberValid() {
		String operationNumber = "030";
		boolean result = cncProgramValidator.isOperationNumberValid(operationNumber);
		assertTrue(result);
	}

	@Test
	public void isDatailNameValid() {
		String detailName = "7555-1712004";
		boolean result = cncProgramValidator.isDatailNameValid(detailName);
		Assert.assertTrue(result);
	}

	@Test
	public void isProgramTextValid() {
		String programText = "N1 ;detal 7548-1712212-10 korpus zolotnikov"
				+ "N2 ;OPER 130 ******YSTANOV III****B0/B180/B270***G57*******"
				+ "N3 ;STANOK nbh-6 INV.zav. A.10003135.02" 
				+ "N4 ;KOZAKEVICH D.O          15.07.11"
				+ "N5 ;TC=29.5_MIN +DOBAVIL(RAZ-S P-P CENT.SV D12_L=6,D11_L=5.6,D8.5_L=112.7)"
				+ "N6 DEF REAL POM_Y,FUS=3000,ZBEZ,ZPRIPUSK,XBEZ,YBEZ,ZAG,RO,Y_B180,X_B180,Z_B180,Y_B0,X_B0,Z_B0,Y_B270,X_B270,Z_B270,DPR,KP,TF,TS"
				+ "N7 ;DEFINE SMENA_ZYX AS G75 FP=1 Z1=0 Y1=0 X1=0 SPOS=0 "
				+ "N8 DEFINE SMENA_Z AS g0 g53 z930 d0 m5 m9" 
				+ "N9 $AC_TIMER[1]=0  ;cikl starta time/min"
				+ "N10 ;**PERENOS S OBKATKI *R10(OS_X) R20(OS_Y)***"
				+ "N11 R40=315-257.01            ;OS_X  YSTANOV 3 X=315-257(257.72)"
				+ "N12 R50=175                   ;OS_Y  YSTANOV 3"
				+ "N13 R61=-315+365.09           ;OS_Z  YSTANOV 3 !!!B180!!!"
				+ "N14 R60=100-R61               ;OS_Z  YSTANOV 3 !!!B0!!!"
				+ "N15 ;*****************************" 
				+ "N16 ;*********** VREMIA PO INSTR********************"
				+ "N17 ;T2.19(2/19)FREZA D=125/chern.      4_MIN"
				+ "N18 ;T11.2(2/11)FREZA D=40            0.5_MIN"
				+ "N19 ;T6.1(1/6) SVERLO D=16<90         0.5_MIN" 
				+ "N20 ;T29.2(2/29) RAZVERTKA K=3/4"
				+ "N21 ;T3.2(2/3) zenkovka d=10/90       0.5_MIN"
				+ "N22 ;T28.2(2/28) METCIK K=3/4           1_MIN"
				+ "N23 ;T14.2(2/14) FREZA D=125          4.5_MIN"
				+ "N24 ;T6.1(1/6)SVERLO D=16<90            3_MIN"
				+ "N25 ;T29.1(1/29)SVERLO D=6.8          4.5_MIN"
				+ "N26 ;T14.1(1/14)SVERLO D=10.2*        0.5_MIN"
				+ "N27 ;T21.2(2.21)SVERLO D=11             1_MIN"
				+ "N28 ;T25.1(1/25) SVERLO D=8.5         3.5_MIN"
				+ "N29 ;T24.1(1.24) SVERLO D=6/DL*       1.5_MIN"
				+ "N30 ;**************************************************" 
				+ "N31 GOTOF _proverka"
				+ "N32 START_1:" 
				+ "N33 ; ********rabota*********G57*****" 
				+ "N34 ST1: WORK_STEP(1)"
				+ "N35 SMENA_Z" 
				+ "N36 G0 G57 G17 G40 G90 G94          ;g57!!!!" 
				+ "N37 T=2.19 M6"
				+ "N38 MSG(T2.19(2/19) FREZA D=125,WORK_STEP(1) )"
				+ "N39 ;************************************************ T2.19" 
				+ "N40 G0 g57 B0"
				+ "N41 TRANS X=X_B0 Z=Z_B0 Y=Y_B0           ; B0!!!L5" 
				+ "N42 REGIME(80,125,7,0.127)"
				+ "N43 G0 G56 X-148 Y137 D1 M3" 
				+ "N44 Z0.5" 
				+ "N45 G1 Y30 F=r93 M8" 
				+ "N46 X71"
				+ "N47 SMENA_Z" 
				+ "N48 G0 G57 B180" 
				+ "N49 TRANS X=X_B180 Z=Z_B180 Y=Y_B180    ; B180!!!L16"
				+ "N50 G0 X-73 Y137 D1 M3" 
				+ "N51 Z0.5" 
				+ "N52 G1 Y30 F=r93 M8" 
				+ "N53 X135"
				+ "N54 SMENA_Z" 
				+ "N55 ST2: WORK_STEP(2)" 
				+ "N56 T=11.2 M6"
				+ "N57 MSG(T11.2(2/11)FREZA D=40,WORK_STEP(2))"
				+ "N58 ;*************************************************** T11.2" 
				+ "N59 G0 g57 B270"
				+ "N60 TRANS X=X_B270 Z=Z_B270 Y=Y_B270           ; B270_!!!L7" 
				+ "N61 REGIME(110,40,3,0.06)"
				+ "N62 G0 G57 X0 Y85 D1 M3" 
				+ "N63 Z0" 
				+ "N64 G1 Y38 F=r93" 
				+ "N65 G0 Z40"
				+ "N66 SMENA_Z";
		boolean result = cncProgramValidator.isProgramTextValid(programText);
		assertTrue(result);
	}

	@Test
	public void isCommentValid() {
		String comment = "добавил проход на проходной черн.";
		boolean result = cncProgramValidator.isCommentValid(comment);
		Assert.assertTrue(result);
	}

	@Test
	public void isCncMachineModelValid() {
		String model = "NBH-6";
		boolean result = cncProgramValidator.isCncMachineModelValid(model);
		Assert.assertTrue(result);
	}

	@Test
	public void isCncMachineCodeEquipmentValid() {
		String codeEquipment = "067";
		boolean result = cncProgramValidator.isCncMachineCodeEquipmentValid(codeEquipment);
		Assert.assertTrue(result);
	}

	@Test
	public void isCncProgramValid() {
		Detail detail = new Detail("7555-41201063");
		CncMachine cncMachine = new CncMachine("ИР500", 113);
		CncProgram cncProgram = new CncProgram("150_06105113", 050, "%\r\n"
				+ "N001G50X90000Z48000T01S11\r\n"
				+ "N002G00X41700Z20000H01M08\r\n"
				+ "N003G99Z5200M04\r\n"
				+ "N004G01Z1200F35\r\n"
				+ "N005G00X42000Z4650 \r\n"
				+ "N006G01X37500\r\n"
				+ "N007G00X41800Z4800 \r\n"
				+ "N008G01Z4430F200 \r\n"
				+ "N009X37500F35\r\n"
				+ "N010G00X41800Z4500 \r\n"
				+ "N011G01Z4220F200 \r\n"
				+ "N012X37500F35\r\n"
				+ "N013G00X41060Z4300 \r\n"
				+ "N014G01Z1200 \r\n"
				+ "N015G00X41200Z4300 \r\n"
				+ "N016X40400 \r\n"
				+ "N017G01X41200Z3620 \r\n"
				+ "N018G00X90000Z48000H00M05\r\n"
				+ "N019G50X94000Z40000T02S21\r\n"
				+ "N020G00X19700Z20000H02 \r\n"
				+ "N021Z3600M04 \r\n"
				+ "N022G01X31000F35 \r\n"
				+ "N023G00X19800Z3700 \r\n"
				+ "N024G01Z3400F200 \r\n"
				+ "N025X31000F35\r\n"
				+ "N026G00X19800Z3500 \r\n"
				+ "N027G01Z3050F200 \r\n"
				+ "N028X31000F35\r\n"
				+ "N029G00X38500Z4300S11\r\n"
				+ "N030G01X39000F200\r\n"
				+ "N031Z2950F35 \r\n"
				+ "N032G00X38800Z4300 \r\n"
				+ "N033X40150 \r\n"
				+ "N034G01X39650Z4050 \r\n"
				+ "N035Z2950\r\n"
				+ "N036X39000 \r\n"
				+ "N037X38000Z2600\r\n"
				+ "N038G00Z20000\r\n"
				+ "N039X94000Z40000H00M05 \r\n"
				+ "N040G50X90000Z48000T03S11\r\n"
				+ "N041G00X60000Z4200H03\r\n"
				+ "N042X41500M04\r\n"
				+ "N043G01X39000F30 \r\n"
				+ "N044G00X40160Z4300 \r\n"
				+ "N045G01X40950Z3620F10\r\n"
				+ "N046Z1200\r\n"
				+ "N047G00X60000\r\n"
				+ "N048X90000Z48000H00M05 \r\n"
				+ "N049G50X94000Z40000T04S31\r\n"
				+ "N050G00X20000Z20000H04 \r\n"
				+ "N051Z3200M04 \r\n"
				+ "N052G01Z2750F100 \r\n"
				+ "N053X20500Z3000F12 \r\n"
				+ "N054X31200 \r\n"
				+ "N055G00Z20000M09 \r\n"
				+ "N056X94000Z40000H00M05 \r\n"
				+ "N057G50X90000Z48000T01 \r\n"
				+ "N058G98M74 \r\n"
				+ "N059G22M19 \r\n"
				+ "N060G28B0T11M25\r\n"
				+ "N061G00B0S71 \r\n"
				+ "N062M06\r\n"
				+ "N063X23000Z15000C0H11M08 \r\n"
				+ "N064Z3200M13 \r\n"
				+ "N065G82Z2270B12000L3F70P50 \r\n"
				+ "N066G80M29 \r\n"
				+ "N067G00X25000C1500 \r\n"
				+ "N068G82Z2270B3000L12F70P50 \r\n"
				+ "N070G80M29 \r\n"
				+ "N300G00X35000C1500 \r\n"
				+ "N301Z2500\r\n"
				+ "N302G82Z1000B18000L2F70P50 \r\n"
				+ "N303G80M29 \r\n"
				+ "N071G00Z15000T12 \r\n"
				+ "N072X68000Z43000H00M05 \r\n"
				+ "N073M06\r\n"
				+ "N074G00X25000Z15000C1500H12\r\n"
				+ "N075Z3200M13 \r\n"
				+ "N076G87Z1300B3000L12F80Q300\r\n"
				+ "N077G80M29 \r\n"
				+ "N078G00Z15000M09T13\r\n"
				+ "N079X68000Z43000H00M05 \r\n"
				+ "N310M06\r\n"
				+ "N311G00X35000Z15000H13C0H13\r\n"
				+ "N312Z2500C1500M08\r\n"
				+ "N313G87Z-400B18000L2F70Q300\r\n"
				+ "N314G80M29 \r\n"
				+ "N315G00Z15000M09 \r\n"
				+ "N316X68000Z55000H00M05 \r\n"
				+ "N080M63\r\n"
				+ "N081M06\r\n"
				+ "N082M73\r\n"
				+ "N083X90000Z48000 \r\n"
				+ "N084M30\r\n"
				+ "%\r\n"
				+ "", "добавил фаску <30гр.", detail, cncMachine);
		boolean result = cncProgramValidator.isCncProgramValid(cncProgram);
		Assert.assertTrue(result);
	}

	@Test
	public void isDetailValid() {
		Detail detail = new Detail();
		detail.setName("7555-41201063");
		boolean result = cncProgramValidator.isDetailValid(detail);
		Assert.assertTrue(result);
	}

	@Test
	public void isCncMachineValid() {
		CncMachine cncMachine = new CncMachine();
		cncMachine.setModel("LM70-AT");
		cncMachine.setCodeEquipment(120);
		boolean result = cncProgramValidator.isCncMachineValid(cncMachine);
		assertTrue(result);
	}

}