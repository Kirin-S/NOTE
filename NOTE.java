import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Collections;

public class NOTE implements Serializable {

	String surName, name, phoneNum;
	int year, month, day;
	LocalDate birth;

	NOTE() {
		surName = "";
		name = "";
		phoneNum = "";
		birth = LocalDate.of(1, 1, 1);
	}

	NOTE(String surNam, String nam, String phonNum, int y, int m, int d) {
		surName = surNam;
		name = nam;
		phoneNum = phonNum;
		birth = LocalDate.of(y, m, d);
	}
	
	public void showInfo() {
		System.out.println();
		System.out.println("Surname: " + surName);
		System.out.println("Name: " + name);
		System.out.println("Phone number: " + phoneNum);
		System.out.println("Date of birth: " + birth);
	}
	
	public static void groupByPhone(List<NOTE> p) {
		
		String shortPhone, shortPhone2;
		boolean tr = true;

		while (tr) {
			tr = false;

			for (byte i = 0; i < p.size()-1; i++) {
				shortPhone = p.get(i).phoneNum.substring(0, 3);
				shortPhone2 = p.get(i+1).phoneNum.substring(0, 3);

				if (Integer.parseInt(shortPhone2) < Integer.parseInt(shortPhone)) {
					Collections.swap(p, i, i+1);
					tr = true;
				}
			}
		}
		
	}

	public static NOTE searchByName(List<NOTE> p, String field) {

		NOTE n = new NOTE();

		for (NOTE i: p) {
			if (i.surName.equals(field)) {
				n = i;
			}
		}

		return n;

	}

}