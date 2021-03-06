abstract class Contact{
	private String date;
	private boolean check;
	public Contact(String date){
		this.date=date;
	}
	public boolean isNewerThan(Contact c){
		String p1[]=this.date.split("-");
		String p2[]=c.date.split("-");
		if(Integer.parseInt(p1[0])<Integer.parseInt(p2[0])) return false;
		else if(Integer.parseInt(p1[0])==Integer.parseInt(p2[0])){
		if(Integer.parseInt(p1[1])<Integer.parseInt(p2[1])) return false;
		else if(Integer.parseInt(p1[1])==Integer.parseInt(p2[1]))
		if(Integer.parseInt(p1[2])<Integer.parseInt(p2[2])) return false;}
		return true;
	}
	public void setCheck(boolean a){
		this.check=a;
	}
	public boolean getCheck(){
		return check;
	}
	public String getPhone(){
		return "";
	}
	public String getEmail(){
		return "";
	}
	public abstract String getType();
}
class EmailContact extends Contact{
	private String email;
	public EmailContact(String date, String email){
		super(date);
		super.setCheck(false);
		this.email=email;
	}
	@Override
	public String getEmail(){
		return email;
	}
	public String getType(){
		return String.format("%s","Email");
	}
}
class PhoneContact extends Contact{
	private String phone;
	enum Operator { VIP, ONE, TMOBILE }
	public PhoneContact(String date, String phone){
		super(date);
		super.setCheck(true);
		this.phone=phone;
	}
	@Override
	public String getPhone(){
		return phone;
	}
	public Operator getOperator(){
		String s=""+phone.charAt(2);
		int check=Integer.parseInt(s);
		if(check<=3) return Operator.TMOBILE;
		if(check==5||check==6) return Operator.ONE;
		return Operator.VIP;
	} 
	public String getType(){
		return String.format("%s","Phone");
	}
}
class Student{
	private String firstName,lastName,city;
	private int age,br,brE,brP;
	private long index;
	private Contact[] c;
	public Student(String firstName, String lastName, String city, int age, long index){
		this.firstName=firstName;
		this.lastName=lastName;
		this.city=city;
		this.age=age;
		this.index=index;
		c=new Contact[100];
		br=brE=brP=0;
	}
	public void addEmailContact(String date, String email){
		c[br++]=new EmailContact(date,email);
		brE++;
	}
	public void addPhoneContact(String date, String phone){
		c[br++]=new PhoneContact(date,phone);
		brP++;
	}
	public Contact[] getEmailContacts(){
		Contact[] a=new Contact[brE];
		int br1=0;
		for(int i=0;i<br;i++)
			if(!c[i].getCheck())
			a[br1++]=c[i];
		return a;
	}
	public Contact[] getPhoneContacts(){
		Contact[] a=new Contact[brP];
		int br1=0;
		for(int i=0;i<br;i++)
			if(c[i].getCheck())
			a[br1++]=c[i];
		return a;
	}
	public String getCity(){
		return city;
	}
	public String getFullName(){
		return String.format("%s %s",firstName,lastName);
	}
	public long getIndex(){
		return index;
	}
	public Contact getLatestContact(){
		return c[br-1];
	}
	public int getNumb(){
		return br;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"ime\"" + ":");
		sb.append("\"" + firstName + "\"");
		sb.append(", \"prezime\"" + ":");
		sb.append("\"" + lastName + "\"");
		sb.append(", \"vozrast\"" + ":");
		sb.append(age);
		sb.append(", \"grad\"" + ":");
		sb.append("\"" + city + "\"");
		sb.append(", \"indeks\"" + ":");
		sb.append(index);
		sb.append(", \"telefonskiKontakti\"" + ":[");
		Contact[] a=getEmailContacts();
		Contact[] b=getPhoneContacts();
		for (int i = 0; i < brP; i++) {
		if(i+1==brP) sb.append("\"" + b[i].getPhone() + "\"");
		else{
		sb.append("\"" + b[i].getPhone() + "\"");
		sb.append(", ");
		}
		}
		sb.append("], ");
		sb.append("\"emailKontakti\"" + ":[");
		for (int i = 0; i < brE; i++) {
		if(i+1==brE) sb.append("\"" + a[i].getEmail() + "\"");
		else{
		sb.append("\"" + a[i].getEmail() + "\"");
		sb.append(", ");}
		}
		sb.append("]}");
		return sb.toString();
		}
	} 

public class Faculty{
	private String name;
	private int k;
	private Student[] students;
	public Faculty(String name, Student [] students){
		this.name=name;
		k=students.length;
		this.students=new Student[k];
		for(int i=0;i<k;i++)
			this.students[i]=students[i];
	}
	public int countStudentsFromCity(String cityName){
		int br=0;
		for(int i=0;i<students.length;i++)
			if(this.students[i].getCity()==cityName) br++;
		return br;
	}
	public Student getStudent(long index){
		for(int i=0;i<k;i++)
			if(this.students[i].getIndex()==index) return students[i];
		return null;
		}
	public double getAverageNumberOfContacts(){
		double sum=0;
		for(int i=0;i<k;i++)
			sum+=students[i].getNumb();
		return sum/k;
	}
	public Student getStudentWithMostContacts(){
		int max=students[0].getNumb();
		int j=0;
		for(int i=1;i<k;i++)
			if(max<students[i].getNumb()) {max=students[i].getNumb(); j=i;}
		return students[j];
	}
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("{\"fakultet\"" + ":");
		sb.append("\"" + name + "\"");
		sb.append(", \"studenti\"" + ":[");
		for(int i=0;i<k;i++)
		if(i+1==k) sb.append("\"" + students[i] + "\"");
		else{
		sb.append("\"" + students[i] + "\"");
		sb.append(", ");
		}
		sb.append("]}");
		return sb.toString();
	}
	public static void main(String[] args){
		Student[] s=new Student[2];
		s[0]=new Student("Ema","Jovanova","Bitola",29,71449817);
		s[1]=new Student("Filip","Petrov","Bitola",24,60872678);
		/*s[0].addEmailContact("2012-04-10","sanja@ef.ukim.mk");
		s[0].addEmailContact("2012-04-10","sanja@exm");
		s[1].addPhoneContact("2010-03-05","070/744-235"); */
		//System.out.println();
		s[0].addPhoneContact("2013-08-29","078/327-115");
		s[1].addPhoneContact("2003-03-06","070/283-212");
		s[1].addEmailContact("2014-12-01","bndsljf@finki.ukim.mk");
		s[0].addPhoneContact("2006-06-18","076/927-126");
		s[0].addPhoneContact("2006-06-18","076/927-126");
		s[0].addEmailContact("2014-12-01","bndsljf@finki.ukim.mk");
		s[1].addPhoneContact("2006-06-18","070/333-333");
		//System.out.println(a.getPhone());
		//Contact b=s[0].getLatestContact();
		//System.out.println(b.getPhone());
		//System.out.println(s[0]);
		Faculty f=new Faculty("FINKI",s);
		/*System.out.println(f.getAverageNumberOfContacts());
		System.out.println(f.countStudentsFromCity(s[0].getCity()));
		System.out.println(f); */
		Contact latestContact = f.getStudent(71449817)
						.getLatestContact();
		if (latestContact.getType().equals("Email"))
					System.out.println(((EmailContact) latestContact)
							.getEmail());
				if (latestContact.getType().equals("Phone"))
					System.out.println(((PhoneContact) latestContact)
							.getPhone()
							+ " ("
							+ ((PhoneContact) latestContact).getOperator()
									.toString() + ")");

		//Student a=f.getStudentWithMostContacts();
		//Student b=f.getStudent(43009931);
		//System.out.println(a.getFullName());
		//System.out.println(b.getFullName());
		//System.out.println(s1);
		/*Contact[] a=s1.getEmailContacts();
		Contact[] b=s2.getPhoneContacts();
		Contact c=s1.getLatestContact();
		System.out.println(c.getEmail());
		for(int i=0;i<b.length;i++)
		System.out.println(b[i].getPhone()); */
	}

}