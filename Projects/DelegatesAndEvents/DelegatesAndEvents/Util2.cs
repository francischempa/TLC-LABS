using System;

namespace DelegatesAndEvents
{
    public partial class Utils2
    {

        public  void WriteOut(string msg)
        {
            Console.WriteLine(msg); 
        }

 
        public  void Alert(string msg)
        {
            Console.Beep();
            Console.WriteLine("*** {0} ***", msg);
        }

        public double celToFahr(double val) {
            return val * 100.0;
        }

        public static string Foo(Account i) {
            return i.ToString();
        }


        public Func<double, double> MethodA(Func<double, double> args , Func<double, double> a, Func<double, double> b) {
            return args;
        }


        //public delegate void DoSomething(string s);

        //public delegate double Converter(double d);
        //public delegate Action<T> ( T arg );

        public static void Main(string[] args) {

            //Func<object, string> f1 = Utils2.Foo;
            //Console.WriteLine(f1("Hello World"));


            Utils2 ut = new Utils2();
            Action<string> del = Console.WriteLine;   
            del("Second Call");

            //Utils2 ut = new Utils2();
            //Func<double,double> del = ut.celToFahr; 
            //double ret = del(20);
            //Console.WriteLine(ret);

        }

    }
}
