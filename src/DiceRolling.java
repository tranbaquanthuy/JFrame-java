import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



public class DiceRolling extends JFrame 
{
	
	static String USN;
	public static String Username()
	{
		return USN;
	}
	private JFrame frame;
	public DiceRolling()
	{
		frame = new JFrame();
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - 700) / 2);
	    int y = (int) ((dimension.getHeight() - 500) / 2);
		JPanel panel = new JPanel();
		 
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
 
		panel.setLayout(new GridLayout(x, y, 50, 50));
	     
		ConnectSQL player = new ConnectSQL();
		String username =  Login.Username();
		
		JLabel lbloutmoney = new JLabel();
		lbloutmoney.setBounds(10, 172, 383, 25);
		lbloutmoney.setText("Out of money,contact admin to get more");
		lbloutmoney.setVisible(false);
		add(lbloutmoney);
		
		JLabel lblInvalidNumber = new JLabel();
		lblInvalidNumber.setBounds(159, 117, 146, 25);
		lblInvalidNumber.setText("Invalid number");
		lblInvalidNumber.setVisible(false);
		add(lblInvalidNumber);
		
		JLabel money = new JLabel();
        add(money);
	    if (String.valueOf(player.getMoney(username)) != null)		{
		money.setText(String.valueOf(player.getMoney(username.trim())));
		}
		else
		{
			money.setText("error to get money from server");
		}
	
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(10, 44, 115, 25);
		lblNewLabel.setText("Your money: ");
		add(lblNewLabel);
		JTextField text = new JTextField(1);
		text.setBounds(288, 75, 105, 31);
		add(text);
		JTextField text_1 = new JTextField(1);
		text_1.setBounds(159, 75, 105, 31);
		add(text_1);
		JLabel lblYourNumber = new JLabel();
		lblYourNumber.setBounds(159, 44, 125, 25);
		lblYourNumber.setText("Your number: ");
		add(lblYourNumber);
		JButton btnBack = new JButton();
		btnBack.addActionListener(
		         new ActionListener() 
		         {
		        	 
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(player.checkInternet())
			{
				dispose();
				Home nextframe= new Home();
                nextframe.setVisible(true);
			}
                else
				{
					JOptionPane.showMessageDialog(null, "lost internet connection, please check your internet!");
					dispose();
				      Login nextframe= new Login();
                      nextframe.setVisible(true);
				}
			}
		});
		btnBack.setBounds(10, 300, 105, 35);
		btnBack.setText("Back");
		add(btnBack);
		JLabel lblInstruction = new JLabel();
		lblInstruction.setBounds(10, 10, 481, 25);
		lblInstruction.setText("Each Rolling is 200,if you guess right,You will get 2000 !");
	    add(lblInstruction);
	    
		money.setBounds(10, 75, 105, 31);
		JLabel lblBestMatch = new JLabel();
		lblBestMatch.setBounds(411, 78, 126, 28);
		lblBestMatch.setText("You Won!");
		lblBestMatch.setVisible(false);
		add(lblBestMatch);
		    
		    JPanel southJPanel = new JPanel(new GridLayout(1, 3, 5, 10));
			
			JPanel southJPanelsub = new JPanel(new BorderLayout());
			
			add(southJPanelsub,BorderLayout.SOUTH);
			southJPanelsub.add(southJPanel, BorderLayout.WEST);
			southJPanelsub.setPreferredSize(new Dimension(155, 50));
			southJPanelsub.setMinimumSize(new Dimension(155, 50));
			southJPanelsub.setBorder(new EmptyBorder(10, 10, 10, 10));
			southJPanel.add(btnBack );

		JButton btnRollDice = new JButton();
	    btnRollDice.setBounds(10, 112, 105, 35);
		btnRollDice.setText("Roll Dice");
		add(btnRollDice);
		btnRollDice.addActionListener(
		         new ActionListener() // anonymous inner class 
		         {  
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lblBestMatch.setVisible(false);
				lblInvalidNumber.setVisible(false);
				lbloutmoney.setVisible(false);
				if(player.checkInternet() == true )
	              {
				if(text_1.getText().length() < 2)
	{
			if(text_1.getText().toString() == null || text_1.getText().toString().equals("")  )
			{
				System.out.println("wrong");
				JOptionPane.showMessageDialog(null, "Wrong number!! ");
				lblInvalidNumber.setVisible(true);		
			}
			else
			{
				Character c = text_1.getText().charAt(0);
			if(c == null || c.toString().equals("") || Character.isLetter(c))
				{
					System.out.println("wrong");
					JOptionPane.showMessageDialog(null, "Wrong number!! ");
					lblInvalidNumber.setVisible(true);
				}
			else 
				{
			       if(Character.isDigit(c))
			       {
			    	   if(Integer.parseInt(c.toString()) <= 6 && Integer.parseInt(c.toString()) > 0 )
			    	   {
	  if(player.getMoney(username) >= 200)
			    		   {
				System.out.println("true");
				Random rand = new Random();
		        int  n = rand.nextInt(6) + 1;
				text.setText(String.valueOf(n));
				player.depositRollDice(username);
				money.setText(String.valueOf(player.getMoney(username.trim())));
				      if(Integer.parseInt(c.toString()) == Integer.parseInt(text.getText()))
					  {
				       lblBestMatch.setVisible(true);
				       player.winReward(username);
				       money.setText(String.valueOf(player.getMoney(username.trim())));
					   }
			    	   
			    	   else
			    	   {
							
							System.out.println("Wrong number picking");
							money.setText(String.valueOf(player.getMoney(username.trim())));
						}
			       }
	  else
		{
		money.setText(String.valueOf(player.getMoney(username.trim())));
		lbloutmoney.setVisible(true);
		money.setText(String.valueOf(player.getMoney(username.trim())));
		}   
			    	   }
			          else 
			     {
				JOptionPane.showMessageDialog(null, "Wrong number!! ");
				System.out.println("wrong");
				lblInvalidNumber.setVisible(true);
				money.setText(String.valueOf(player.getMoney(username.trim())));
			     }
			
	           }
     
			} 
			 
			}
					
			}
	else
	{
		JOptionPane.showMessageDialog(null, "Your number you choose have to be in 1 to 6!!! ");
	}
			}
				else
				{
					JOptionPane.showMessageDialog(null, "lost internet connection, please check your internet!");
					dispose();
				      Login nextframe= new Login();
                      nextframe.setVisible(true);
				}
			}
			
		
				  
		});
		add(panel);
		setTitle("Dice Rolling Game");
		setSize(ResolutionSetting.x, ResolutionSetting.y);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
