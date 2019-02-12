package optim;

public class func 
{
	static double p()  //стоимость нового
	{
		return 640;
	}
	static double r(int t) //стоимость ремонта
	{
		return 0.07*(1+t)*640;
	}
	static double g(int t) //можно продать за...
	{
		return Math.pow(1.5, -t)*640;
	}
	static double round(double a)
	{
		return (double)Math.round(a*100)/100;
	}
}
