using System;

namespace DelegatesAndEvents
{
    public partial class Utils
    {

        public static void WriteOut(string msg)
        {
            Console.WriteLine(msg);
        }
        public static void www(string msg)
        {
            Console.WriteLine("WWW.{0}.com", msg);
        }
        public static void Alert(string msg)
        {
            Console.Beep();
            Console.WriteLine("*** {0} ***", msg);
        }
        public static double CelToFahr(double d)
        {
            return (d * (9.0 / 5.0)) + 32;
        }
    }
}
