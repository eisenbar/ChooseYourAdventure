import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.concurrent.TimeUnit;

public class FrontGUI extends JFrame {

	private JPanel contentPane;
	public static Adventure1 a;
	private static JTextArea hypothesisArea;
	private static JTextArea questionArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				   
					FrontGUI frame = new FrontGUI();
					frame.setVisible(true);
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
			
		});
		
		String tempQ = "";
		String tempH = "";
		
		 a = new Adventure1();
		 
		while(!a.gameOver){
			
			if(tempQ != a.question){
				setQuestionText(a.question);
				tempQ = a.question;
			}
			
			if(tempH != a.hypothesis){
				setHypothesisText(a.hypothesis);
				tempH = a.parser.hypothesis;
			}
			
		}
		
	}

	/**
	 * Create the frame.
	 */
	public FrontGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				a.main(null);
			}
		});
		
		btnStartGame.setBounds(182, 227, 117, 23);
		contentPane.add(btnStartGame);
		
		JLabel lblQuestion = new JLabel("Question");
		lblQuestion.setBounds(85, 19, 61, 16);
		contentPane.add(lblQuestion);
		
		JLabel lblHypothesis = new JLabel("Hypothesis");
		lblHypothesis.setBounds(316, 19, 80, 16);
		contentPane.add(lblHypothesis);
		
		questionArea = new JTextArea();
		questionArea.setBounds(36, 47, 157, 169);
		contentPane.add(questionArea);
		
		hypothesisArea = new JTextArea();
		hypothesisArea.setBounds(276, 46, 157, 169);
		contentPane.add(hypothesisArea);
		
		
		
		
		
	}
	
	
	
	public static void setHypothesisText(String text){
		
		hypothesisArea.setText(text);
		
		return;
	}
	
	public static void setQuestionText(String text){
		
		questionArea.setText(text);
		
		return;
	}
}
