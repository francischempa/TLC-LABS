using System;
using System.Collections.Generic;
using System.Linq;

namespace Group8
{
    public class Student : Person, IComparable
    { 
        private string school;
        public String School
        {
            get { return school; }
            set { school = value; }
        }

        private string energyLevel;
        public string EnergyLevel
        {
            get { return energyLevel; }
            set { energyLevel = value; }
        }

        private List<double> grades;

        public override double Rating {
            get
            {
                return grades.Average();
            }
        }  

        public Student(string name, string school, string energyLevel, List<double> grades):base(name)
        {
            this.school = school;
            this.energyLevel = energyLevel;
            this.grades = new List<double>(grades);
        }

        public void Listen()
        {
            Console.WriteLine("I am listening");
        }

        public void TakeTest()
        {
            Console.WriteLine("I am taking the test");
        }

        public void AttendLecture()
        {
            Console.WriteLine("I am going to Lecture");
        }

        public override string ToString()
        {
            return string.Format("[Student: school={0}, energyLevel={1}, grades={2}]", school, energyLevel, grades);
        }

        public int CompareTo(object obj)
        {
            string s = ((Student)obj).Name;
            return this.Name.CompareTo((object)s);
        }
 
    }
}
