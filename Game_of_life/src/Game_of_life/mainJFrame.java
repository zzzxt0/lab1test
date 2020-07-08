package Game_of_life;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.Random;
import javax.swing.JButton;

public class mainJFrame extends MouseAdapter{
	static JButton[][] btn=new JButton[99][99];
    static Cell cell[][]=new Cell[99][99];
    int state1[][]=new int [99][99];
    
    static void alter_map() {
		Map.calculate_living(cell);
		for(int i=0;i<99;i++) {
			for(int j=0;j<99;j++) {
				if(cell[i][j].judge()==1) {
					cell[i][j].alter_state(1);
		    		  cell[i][j].state=cell[i][j].get_state();
		    		  btn[i][j].setBackground(Color.red); //在此修改
				}
				else {
					cell[i][j].alter_state(0);
		    	    cell[i][j].state=cell[i][j].get_state();
		    	    btn[i][j].setBackground(Color.white); //在此修改
				}
			}
		}
		/*
		for(int i=0;i<99;i++) {
			for(int j=0;j<99;j++) {
				if(cell[i][j].get_state()==1) {
			    	btn[i][j].setBackground(Color.red);
		    		
				}else {
					btn[i][j].setBackground(Color.white);
				}
			}
		}
		*/
	}
    
	@SuppressWarnings("static-access")
	mainJFrame(){
		int count=0;
		final JFrame mainJFrame=new JFrame("演化"+count);
		mainJFrame.setSize(425,500);
		JPanel jpanel=new JPanel();
		JPanel jpanel1=new JPanel();
		jpanel.setBounds(0,0,400,425);
		jpanel1.setBounds(20,415,400,40);
		mainJFrame.setLocationRelativeTo(null);
		mainJFrame.setDefaultCloseOperation(mainJFrame.EXIT_ON_CLOSE);
		Container container=mainJFrame.getContentPane();
		container.setLayout(null);
		GridLayout grid=new GridLayout(99,99);
		jpanel.setLayout(grid);
		
		cell=Map.initial();
		for(int i=0;i<99;i++) 
		{
			for(int j=0;j<99;j++) 
			{
				btn[i][j]=new JButton();
				btn[i][j].setSize(1,1);
				jpanel.add(btn[i][j]);
				btn[i][j].setBackground(Color.white);
				btn[i][j].addActionListener (new ActionListener() 
				{				
					 public void actionPerformed(ActionEvent e)
					 {
						for(int i=0;i<99;i++)
						{
							for(int j=0;j<99;j++) 
							{
					    	    if(e.getSource()==btn[i][j])
					    	    {
					    		  btn[i][j].setBackground(Color.red);
					    		  cell[i][j].alter_state(1);
					    		  state1[i][j]=1;
					    		  }					    	   
					    	    }
							}			
						}
				});
			}	
		}
		
		final JButton btn1=new JButton("ok");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btn1) {
					Thread thread=new Thread(new Runnable() {					
						public void run() {							
							int count=0;							
							while(true) {
								for(int i=0;i<99;i++) {
									for(int j=0;j<99;j++) {
										if(btn[i][j].getBackground()==Color.red)
											cell[i][j].alter_state(1);
										else
											cell[i][j].alter_state(0);
									}
								}
								alter_map();								
								try {
									Thread.sleep(1000);
								}catch(InterruptedException e1) {
									e1.printStackTrace();
								}
								count++;
					   		    mainJFrame.setTitle("演化"+count);

								if(count>100)
									   break;
						}
						}
					});
					thread.start();	
				}
			}
		});
		btn1.setSize(10,10);
		final JButton btn2=new JButton("随机数");
		btn2.setSize(10,10);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btn2) {
					Thread thread=new Thread(new Runnable() {
						public void run() {
							int count=0;
 				           for(int i=0;i<99;i++) {
					         for(int j=0;j<99;j++) {
						       Random random=new Random();
					           cell[i][j].alter_state(random.nextInt(2));
					           if(cell[i][j].state==1)
					    	     btn[i][j].setBackground(Color.red);
					           else 
					    	     btn[i][j].setBackground(Color.white);
					         }
			               }		
				           while(true) {
					         for(int i=0;i<99;i++) {
						       for(int j=0;j<99;j++) {
							     if(btn[i][j].getBackground()==Color.red)
								   cell[i][j].alter_state(1);
							     else
								   cell[i][j].alter_state(0);
						       }
					         }
					         alter_map();
					         /*
					         for(int i=0;i<99;i++) {
						       for(int j=0;j<99;j++) {
							     if(cell[i][j].state==1)
							    	btn[i][j].setBackground(Color.red);
							     else
								   btn[i][j].setBackground(Color.white);
						       }
					         }
					         */
					   		 count++;
					         mainJFrame.setTitle("演化"+count);
						     try {
						       Thread.sleep(1000);
					         } catch (InterruptedException e1) {
						     // TODO Auto-generated catch block
						          e1.printStackTrace();
					           }	
					           if(count>1000)
						         break;
				          }
					
				       }
			
			       });
				   thread.start();
			   }
		    }
		});
		jpanel1.add(btn2);
		jpanel1.add(btn1);
		
		container.add(jpanel);
        container.add(jpanel1);
		mainJFrame.setVisible(true);
	}

}


