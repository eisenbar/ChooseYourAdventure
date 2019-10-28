
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;

public class ParseAudio {

	private Configuration configuration;

	public ParseAudio(File file) {
		return;
	}

	public ParseAudio() {

		this.configuration = new Configuration();
		configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
		configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
	}

	public String parseInputWav(Configuration configuration, String inputWav) throws Exception {

		String inputString = "";
		StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(configuration);
		InputStream stream = new FileInputStream(new File("test.wav"));

		recognizer.startRecognition(stream);
		SpeechResult result;
		while ((result = recognizer.getResult()) != null) {
			System.out.format("Hypothesis: %s\n", result.getHypothesis());
			inputString = result.getHypothesis();
		}
		recognizer.stopRecognition();

		return inputString;
	}

	public String parseInputStream(String choice1, String choice2) throws Exception {

		String inputStream = "";

		LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(this.configuration);
		recognizer.startRecognition(true);
		SpeechResult result = recognizer.getResult();

		while ((result = recognizer.getResult()) != null) {
			String hypothesis = result.getHypothesis();
			System.out.format("Hypothesis: %s\n", hypothesis);
			if (hypothesis.equalsIgnoreCase("stop")) {
				recognizer.stopRecognition();
				return "stop";
			}
			if (hypothesis.equalsIgnoreCase(choice1)) {
				recognizer.stopRecognition();
				return choice1;
			}
			if (hypothesis.equalsIgnoreCase(choice2)) {
				recognizer.stopRecognition();
				return choice2;
			}
		}
		recognizer.stopRecognition();

		return inputStream;
	}

	// Main method. Only in case of calling this class independently
	public static void main(String[] args) throws Exception {

		// config setup
		ParseAudio p = new ParseAudio();
		Configuration configuration = new Configuration();
		configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
		configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

		// p.parseInputWav(configuration,"hello.wav");
		p.parseInputStream("yes", "no");

		return;
	}
}