package optim;

public class func 
{
	static double p()  //��������� ������
	{
		return 640;
	}
	static double r(int t) //��������� �������
	{
		return 0.07*(1+t)*640;
	}
	static double g(int t) //����� ������� ��...
	{
		return Math.pow(1.5, -t)*640;
	}
	static double round(double a)
	{
		return (double)Math.round(a*100)/100;
	}
}
