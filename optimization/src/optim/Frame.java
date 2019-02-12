package optim;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Frame extends JFrame
{
	public Frame()
	{
		setResizable(true); 
		setBounds(700, 700, 1375, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Замена оборудования");
		setBackground(Color.WHITE);
		setVisible(true);
		setLocationRelativeTo(null);
		
		
		JPanel panel = new JPanel();
		
		JTextField jtf = new JTextField(10); // Создаем поле для ввода текста 10 символов в ширину
        jtf.setHorizontalAlignment(JTextField.CENTER); // ВОТ ОНО! Текст вводится посередине
        panel.add(jtf); // Добавляем в контейнер
 
        panel.setLocation(null); // Размещаем окно в центре экрана
        panel.setVisible(true); // Делаем его видимым
	}
	
	public void paint(Graphics g)
		{
			
			g.clearRect(0, 0, getBounds().width, getBounds().height);
			int glubina=10; //то самое от одного до десяти
			
			
			int num=1, buff=0, y, x, d=0;
			
			for(y=1; y<=glubina; ++y) //считает кол-во необходимых элементов
				{
					buff++;
					num=num+buff;
				}
			
			cycle mass[]=new cycle[num]; //создаётся массив объектов
			for(int u=0; u<=num-1; u++)
				{
					mass[u]=new cycle();
				}
			
			//начинаем раздачу значений 
			
			//_____________________________задание нулевого - начало
			mass[0].n=0;
			mass[0].t=0;
			
			//g.drawString(num+"ыфыа", 500, 500);
			
			mass[0].up=func.p()+func.r(0);
			
			mass[0].c_x=40; //окружность 
			mass[0].c_y=670;
			
			mass[0].up_res_x=92; //значения на стрелках
			mass[0].up_res_y=663;
			
			mass[0].s_up_x=87;//стрелка вверх
			mass[0].s_up_y=681;
			mass[0].f_up_x=146;
			mass[0].f_up_y=656;
			
			mass[0].res_x=45;//число внутри
			mass[0].res_y=699;
			
			//_____________________________задание нулевого - конец
			
			//_____________________________задание остальных - начало
			
			int x1=147, y1=651;
			for (y=1; y<=glubina; ++y) //y - играет роль n
			{ 
				y1=631;
				x=1;
				//rep=x-1;
				while(x<=y)
				{
					d++;
					mass[d].n=y;
					mass[d].t=x;
					mass[d].up=func.r(x);
					mass[d].down=func.p()-func.g(x)+func.r(0);
					
					mass[d].c_x=x1;
					mass[d].c_y=y1;
					
					if(y!=glubina)
					{	
						mass[d].up_res_x=x1+30;
						mass[d].up_res_y=y1;
						mass[d].down_res_x=x1+52;
						mass[d].down_res_y=y1+20;
						
						mass[d].s_up_x=x1+40;
						mass[d].s_up_y=y1+5;
						mass[d].f_up_x=x1+118;
						mass[d].f_up_y=y1-38;
						
						mass[d].s_down_x=x1+50;
						mass[d].s_down_y=y1+29;
						mass[d].f_down_x=x1+121; 
						mass[d].f_down_y=660;	//podymat
					}
					mass[d].res_x=x1+6;//число внутри
					mass[d].res_y=y1+30;
					y1=y1-63; //высота следующего сверху
					x++;
				}
				x1=x1+120;
			}
			
			int r=glubina+1;
			for(int m=num-1; m>=num-1-glubina; m--) //заполнение последнего столбца
			{	
				r--;
				mass[m].res=func.g(r)*(-1);
			}
			
			//__________________________________________________конец задания
			
			
			//________________приступаем к вычислениям
			
			int op=num-glubina-1;
			for (int h=glubina-1; h>=0; h--)
			{
				int cost=op+1;
				for(int ty=mass[op].t; ty>=1; ty--)
				{				
					if(mass[op+mass[op].n+1].res+mass[op].up<mass[cost].res+mass[op].down)
					{
						mass[op].res=mass[op+mass[op].n+1].res+mass[op].up;
						mass[op].ptr=op+mass[op].n+1;
					}
					else
					{
						mass[op].res=mass[cost].res+mass[op].down;
						mass[op].ptr=cost;
					}
					op--;
				}
			}
			mass[0].res=mass[0].up+mass[1].res;
			mass[0].ptr=1;
			
			//____________________ОТРИСОВКА____________
			for(int maxim=0; maxim<=num-1; ++maxim)
			{
			g.drawOval(mass[maxim].c_x, mass[maxim].c_y, 50, 50);
			g.drawLine(mass[maxim].s_up_x, mass[maxim].s_up_y, mass[maxim].f_up_x, mass[maxim].f_up_y);//рисует линию от начала
			g.drawLine(mass[maxim].s_down_x, mass[maxim].s_down_y, mass[maxim].f_down_x, mass[maxim].f_down_y);
			g.drawString(func.round(mass[maxim].up)+"", mass[maxim].up_res_x, mass[maxim].up_res_y);
			g.drawString(func.round(mass[maxim].down)+"", mass[maxim].down_res_x, mass[maxim].down_res_y);
			g.drawString(func.round(mass[maxim].res)+"", mass[maxim].res_x, mass[maxim].res_y);
			}
			g.drawString(func.round(mass[0].up)+"", mass[0].up_res_x, mass[0].up_res_y);
			g.drawString(func.round(mass[0].res)+"", mass[0].res_x, mass[0].res_y);
			
			int i=0;
			for(int qw=0; qw<=glubina; ++qw)
			{
				g.drawOval(mass[i].c_x-2, mass[i].c_y-2, 54, 54);
				g.drawOval(mass[i].c_x-1, mass[i].c_y-1, 52, 52);
				if (mass[i].t<mass[mass[i].ptr].t)
				{
					g.drawLine(mass[i].s_up_x, mass[i].s_up_y+1, mass[i].f_up_x, mass[i].f_up_y+1);
					g.drawLine(mass[i].s_up_x, mass[i].s_up_y-1, mass[i].f_up_x, mass[i].f_up_y-1);
				}
				else
				{
					g.drawLine(mass[i].s_down_x, mass[i].s_down_y+1, mass[i].f_down_x, mass[i].f_down_y+1);
					g.drawLine(mass[i].s_down_x, mass[i].s_down_y-1, mass[i].f_down_x, mass[i].f_down_y-1);
				}
				i=mass[i].ptr;
			}
			
			//______________ОТРИСОВКА_ОКОНЧЕНА____
		}
	}