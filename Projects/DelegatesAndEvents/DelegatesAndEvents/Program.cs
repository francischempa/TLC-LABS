using System;
using System.Linq.Expressions;

namespace DelegatesAndEvents
{
    class MainClass
    {
        public static void Slot_MyOverdrawnHandler(object sender, AccountEventArgs e)
        {
            Console.WriteLine("Overdrawn with balance, {0}", e.Balance);
        }


        public static void Main3(string[] args)
        {
            Account acc = new Account("Chempa Francis", 10);
            acc.signal += Slot_MyOverdrawnHandler;
            acc.Withdraw(90);

        }
        public static void Main2(string[] args)
        {
            Expression<Func<int, int, int>> even = (a, b) => a + b;
            Console.WriteLine("{0}", even.Body);
            if (even.Body is BinaryExpression be)
            {
                Console.WriteLine("{0} : {1}", be.Left, be.Right);
            }
            Func<int, int, int> exec = even.Compile();
            Console.WriteLine(exec(200, 100));
        }



        public delegate void Dosomething(string s);
        public delegate double MyConverter(double d);





        public static void Execute(Action<int, int> fn, int arg1, int arg2)
        {
            fn(arg1, arg2);
        }


        public static void Main1(string[] args)
        {
            Dosomething task = new Dosomething(Utils.WriteOut);
            task += new Dosomething(Utils.www);
            task += new Dosomething(Utils.Alert);

            task("ChempaFrancis");

            MyConverter conv = Utils.CelToFahr;
            Console.WriteLine(conv(53));



            Action<string> task1 = Utils.WriteOut;
            task1 += Utils.www;
            task1 += Utils.Alert;
            task1("ChempaFrancis");

            Func<double, double> conv1 = Utils.CelToFahr;
            Console.WriteLine(conv1(53));

            Predicate<int> IsEven = (int a) => a % 2 == 0;
            Console.WriteLine(IsEven(4));

            Comparison<char> ccomp = (char a, char b) => a - b;
            Console.WriteLine(ccomp('a', 'b'));

            Converter<char, int> conv2 = (char c) => (int)c;

            Execute((arg1, arg2) => Console.WriteLine(arg1 + arg2), 100, 200);
        }
    }
}
