import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class final02 {
	static void loadStudentScore() {

		scoreMap.clear();

		try {

			BufferedReader br = new BufferedReader(new FileReader("C:\\temp\\score.txt"));

			String line;

			while ((line = br.readLine()) != null) {

				String[] data = line.split(",");

				String name = data[0];

				int mid = Integer.parseInt(data[1]);

				int fin = Integer.parseInt(data[2]);

				int task = Integer.parseInt(data[3]);

				Student student = new Student(name, mid, fin, task);

				scoreMap.put(name, student);
			}

			br.close();

		} catch (Exception e) {
			System.out.println("파일 읽기 오류");
		}
	}

	static String getGrade(int score) {

		if (score >= 90)
			return "A+";
		else if (score >= 85)
			return "A0";
		else if (score >= 80)
			return "B+";
		else if (score >= 75)
			return "B0";
		else if (score >= 70)
			return "C+";
		else if (score >= 65)
			return "C0";
		else if (score >= 60)
			return "D+";
		else if (score >= 55)
			return "D0";
		else
			return "F";
	}

	static class Student {
		String name;
		int mid;
		int fin;
		int task;

		Student(String name, int mid, int fin, int task) {
			this.name = name;
			this.mid = mid;
			this.fin = fin;
			this.task = task;
		}
	}

	static class Friend {

		String name;
		int age;
		String phone;
		String major;
		String address;

		Friend(String name, int age, String phone, String major, String address) {

			this.name = name;
			this.age = age;
			this.phone = phone;
			this.major = major;
			this.address = address;
		}
	}

	static LinkedHashMap<String, Friend> jusoMap = new LinkedHashMap<>();
	static HashMap<String, Student> scoreMap = new HashMap<>();

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		while (true) {
			System.out.println("\n1. 자기 소개");
			System.out.println("2. 학생성적 입력");
			System.out.println("3. 학생성적 학점 출력");
			System.out.println("4. 학생 검색");
			System.out.println("5. 계산기 프레임");
			System.out.println("6. 학생 주소록 저장, 출력");
			System.out.println("7. 끝내기");

			int menu;
			try {
				menu = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				continue;
			}

			switch (menu) {
			case 1:
				intro();
				break;
			case 2:
				addStudentScore();
				break;

			case 3:
				printStudntGrade();
				break;
			case 4:
				searchStudent();
				break;
			case 5:
				calculatorFrame();
				break;
			case 6:
				friendAddressBook();
				break;
			case 7: {
				return;
			}
			}
		}
	}

	static void intro() {
		System.out.println("안녕하세요! 저는 25학번 배승재이라합니다.");
		System.out.println("전공은 컴공이며, 미래 직업인 풀 스택 개발자를 목표로 열심히 공부하고 있습니다.");
	}

	static void addStudentScore() {

		scoreMap.clear();

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\temp\\score.txt"));

			System.out.println("학생 이름과 이산구조과목의 중간성적(40), 기말성적(40), 과제성적(20)을 입력하세요.");

			for (int i = 0; i < 5; i++) {

				System.out.print(">> ");
				String input = sc.nextLine();

				String[] data = input.split(",");

				if (data.length != 4) {
					System.out.println("입력 형식 오류!");
					i--;
					continue;
				}

				String name = data[0];
				int mid = Integer.parseInt(data[1]);
				int fin = Integer.parseInt(data[2]);
				int task = Integer.parseInt(data[3]);

				Student student = new Student(name, mid, fin, task);

				scoreMap.put(name, student);

				bw.write(input);
				bw.newLine();
			}

			bw.close();

			System.out.println("총 " + scoreMap.size() + "개의 학생성적을 읽었습니다.");

		} catch (Exception e) {
			System.out.println("파일 저장 오류");
		}
	}

	static void printStudntGrade() {

		loadStudentScore();

		if (scoreMap.isEmpty()) {
			System.out.println("저장된 학생 정보가 없습니다.");
			return;
		}

		System.out.println("---------------------------------------------");

		int count = 1;
		for (Student student : scoreMap.values()) {

			int sum = (student.mid + student.fin + student.task);

			String grade = getGrade(sum);

			System.out.println("이름 : " + student.name);
			System.out.println("중간 : " + student.mid);
			System.out.println("기말 : " + student.fin);
			System.out.println("과제 : " + student.task);
			System.out.println("합계 : " + sum);
			System.out.println("학점 : " + grade);

			count++;
			if (count <= scoreMap.size()) {
				System.out.println("---------------------------------------------");
				System.out.println("---------------------------------------------");
			}
		}
		System.out.println("---------------------------------------------");
	}

	static void searchStudent() {
		loadStudentScore();

		if (scoreMap.isEmpty()) {
			System.out.println("저장된 학생 정보가 없습니다.");
			return;
		}

		while (true) {

			System.out.println("검색할 이름을 입력하세요.");
			System.out.print("이름 >> ");

			String name = sc.nextLine();

			if (name.equals("그만")) {
				break;
			}

			Student student = scoreMap.get(name);

			if (student == null) {
				System.out.println("해당 학생이 존재하지 않습니다.");
				continue;
			}

			int total = student.mid + student.fin + student.task;

			String grade = getGrade(total);

			System.out.println("중간성적 : " + student.mid + ", 기말성적 : " + student.fin + ", 과제성적 : " + student.task
					+ ", 학점 : " + grade);
		}
	}

	static void calculatorFrame() {

		JFrame frame = new JFrame("계산기 프레임");
		frame.setSize(420, 390);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		JTextField input = new JTextField(20);
		JTextField result = new JTextField(20);
		result.setEditable(false);

		JPanel topPanel = new JPanel(new BorderLayout(10, 10));
		topPanel.setBackground(new Color(255, 220, 230));
		topPanel.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

		JLabel inputLabel = new JLabel("수식입력");
		topPanel.add(inputLabel, BorderLayout.WEST);
		topPanel.add(input, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));
		buttonPanel.setBackground(Color.WHITE);

		String[] btns = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "CE", "계산", "+", "-", "*", "/" };

		for (String b : btns) {

			JButton btn = new JButton(b);

			if (b.equals("+") || b.equals("-") || b.equals("*") || b.equals("/")) {

				btn.setBackground(new Color(100, 180, 255));
			}

			btn.addActionListener(e -> {

				String t = btn.getText();

				if (t.equals("CE")) {

					input.setText("");
					result.setText("");

				} else if (t.equals("계산")) {

					try {

						String exp = input.getText();
						double r = simpleCalc(exp);

						result.setText(String.valueOf(r));

					} catch (Exception ex) {

						result.setText("Error");
					}

				} else {

					input.setText(input.getText() + t);
				}
			});

			buttonPanel.add(btn);
		}

		JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
		bottomPanel.setBackground(Color.YELLOW);
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));

		JLabel resultLabel = new JLabel("계산 결과");

		result.setPreferredSize(new Dimension(150, 25));

		JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
		leftPanel.setBackground(Color.YELLOW);

		leftPanel.add(resultLabel);
		leftPanel.add(result);

		bottomPanel.add(leftPanel, BorderLayout.WEST);

		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.CENTER);
		frame.add(bottomPanel, BorderLayout.SOUTH);

		frame.setVisible(true);
	}

	static void friendAddressBook() {

		jusoMap.clear();

		try {

			BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\temp\\juso.txt"));

			bw.write("---------------------------------------------------------------------------------");
			bw.newLine();

			bw.write(String.format("%-6s %-10s %-6s %-18s %-10s %-20s", "행번호", "이름", "나이", "연락처", "전공", "주소"));
			bw.newLine();

			bw.write("---------------------------------------------------------------------------------");
			bw.newLine();

			for (int i = 0; i < 3; i++) {

				System.out.print("이름 입력 ==> ");
				String name = sc.nextLine();

				System.out.print("나이 입력 ==> ");
				int age = Integer.parseInt(sc.nextLine());

				System.out.print("전화번호 입력 ==> ");
				String phone = sc.nextLine();

				System.out.print("전공 입력 ==> ");
				String major = sc.nextLine();

				System.out.print("주소 입력 ==> ");
				String address = sc.nextLine();

				Friend friend = new Friend(name, age, phone, major, address);

				jusoMap.put(name, friend);

				bw.write(String.format("%-6d %-10s %-6d %-18s %-10s %-20s", i + 1, name, age, phone, major, address));

				bw.newLine();
			}

			bw.close();

			System.out.println("---------------------------------------------------------------------------------");
			System.out.printf("%-6s %-10s %-6s %-18s %-10s %-20s%n", "행번호", "이름", "나이", "연락처", "전공", "주소");
			System.out.println("---------------------------------------------------------------------------------");

			int row = 1;

			for (Friend f : jusoMap.values()) {

				System.out.printf("%-6d %-10s %-6d %-18s %-10s %-20s%n", row++, f.name, f.age, f.phone, f.major,
						f.address);
			}

		} catch (Exception e) {
			System.out.println("주소록 저장 오류 : " + e.getMessage());
		}
	}

	static double simpleCalc(String exp) {
		char op = ' ';
		int pos = -1;

		for (char c : new char[] { '+', '-', '*', '/' }) {
			pos = exp.indexOf(c);
			if (pos > 0) {
				op = c;
				break;
			}
		}

		double a = Double.parseDouble(exp.substring(0, pos));
		double b = Double.parseDouble(exp.substring(pos + 1));

		return switch (op) {
		case '+' -> a + b;
		case '-' -> a - b;
		case '*' -> a * b;
		case '/' -> a / b;
		default -> 0;
		};
	}

}
