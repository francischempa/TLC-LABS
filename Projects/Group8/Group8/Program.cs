using System;
using System.Collections.Generic;

namespace Group8
{
    class MainClass
    {
        public static void Work(TimeSpan ts) {
            Console.WriteLine("The program took {0} milliseconds to execute.", ts.TotalMilliseconds);
        }

        public static void Main(string[] args)
        {
            using (Timer t = new Timer("Message: this is the message"))
            {
                List<double> gradesOrEval = new List<double>() { 100, 100, 100, 100, 100 };
                String[] student1Data = { "Zhempa Francis", "UCC", "100" };
                String[] student2Data = { "Oheneba Wisdom", "UCC", "-100" };
                String[][] data = { student1Data, student2Data };

                Student[] studentsList = new Student[Int32.Parse(args[1])];
                for (int i = 0; i < studentsList.Length; i++)
                {
                    studentsList[i] = new Student(data[i][0], data[i][1], data[i][2], gradesOrEval);
                }
                foreach (Student std in studentsList)
                {
                    Console.WriteLine(std);
                }

                Array.Sort(studentsList);
                Console.WriteLine("\n");

                foreach (Student std in studentsList)
                {
                    Console.WriteLine(std);
                }
            }

        }
    }
}

