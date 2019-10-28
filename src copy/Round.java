/*
 * Data pulled from the input JSON will be stored like this
 */
public class Round {
	public String text;
	public String question;
	public String option1;
	public String option2;
	public int result1;
	public int result2;
	
	public Round(){
		this.text = "";
		this.question  = "";
		this.option1 = "";
		this.option2 = "";
		this.result1 = -1;
		this.result2 = -1;
	}
	public Round(String txt, String q, String o1, String o2, int r1, int r2){
		this.text = txt;
		this.question  = q;
		this.option1 = o1;
		this.option2 = o2;
		this.result1 = r1;
		this.result2 = r2;
	}
}
