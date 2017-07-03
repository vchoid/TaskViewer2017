package de.gokv.client.taskviewer.TestMe.Tutorial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Test {

	static List<String> names = Arrays.asList("KRUPPIO", "Krahn", "Mayer", "Müller", "Schulze", "Klock", "Fiedel",
			"albert", "1234");

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String searchVal = "KR*";
		searchVal = searchVal.replaceAll("\\*", "\\\\w*");
		System.out.println(searchVal);
		System.out.println(filter(searchVal));

	}

	public static Collection<String> filter(String foundVal) {
		List<String> list = new ArrayList<String>();
		for (String name : names) {
			if (name.matches(foundVal))
				list.add(name);
		}
		if (list.isEmpty())
			return null;
		else
			return list;
	}

}
