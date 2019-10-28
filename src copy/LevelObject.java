import java.util.*;
import java.io.File; 
import java.io.FileNotFoundException; 
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*
 * Level Object is just an array of rounds.
 */
public class LevelObject {

	//Level of rounds
	private ArrayList<Round> level;
	
	//constructor
	public LevelObject(String level){
		
		parseLevel(level);
        return;
	}

	//return Rounds object
	public Round getRound(int index){
		return this.level.get(index);
	}
	
	//Parses the level into an arrayList of Rounds
	private void parseLevel(String l){
		
		JSONParser jsonParser = new JSONParser();
		this.level = new ArrayList<Round>();
		
		try{
        Object obj = jsonParser.parse(l);
        JSONObject lvl = (JSONObject)obj;
        
        //get all the rounds
        int numRounds = lvl.size();
       
        for(int i = 0; i < numRounds; i ++){
        	String s = "step" + (i + 1);
        	JSONObject rnd = (JSONObject)lvl.get(s);
        	this.level.add(parseRound(rnd));
        }
        
		}
		catch (ParseException e){
        	e.printStackTrace();
        }
        
		return;
	}
	private Round parseRound(JSONObject round){
		
		Round r = new Round();
		r.text = round.get("text").toString();
		r.question = round.get("question").toString();
		r.option1 = round.get("option1").toString();
		r.option2 = round.get("option2").toString();
		r.result1 = Integer.parseInt(round.get("result1").toString());
		r.result2 = Integer.parseInt(round.get("result2").toString());
	
		return r;
	}
}
