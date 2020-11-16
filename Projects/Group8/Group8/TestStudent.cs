using System;
using System.Collections.Generic;
using NUnit.Framework;

namespace Group8
{
    [TestFixture]
    public class TestStudent
    {
        [Test]
        public void TestOne()

        //    170 
        //6 4 1 pin em 
        {
            Person p = new Student("Chempa", "UCC", "100", new List<double>() { 100, 100, 100, 100, 100 });
            Assert.AreEqual(100, p.Rating);
        }

        [Test]
        public void TestTwo()
        {
            List<double> gradesOrEval = new List<double>() { 100, 100, 100, 100, 100 };
            List<Person> personList = new List<Person>();

            Person p1 = new Student("Chempa", "UCC", "100", gradesOrEval);
            Person p2 = new Instructor("Francis", gradesOrEval);

            personList.Add(p1);
            personList.Add(p2);

            foreach (Person p in personList)
            {
                Assert.AreEqual(100, p.Rating);
            }

        }

        [Test]
        public void TestThree()
        {
            Person p = new Student("Chempa", "UCC", "100", new List<double>() { 100, 100, 100, 100, 100 });
            Assert.AreEqual(100, p.Rating);
        }

        [Test]
        public void TestFour()
        {
            //Timer t = new Timer("Timing the program");
            //for(int i = 0;i< 100000; i++) {
            //    int a = 4;
            //    a += i;
            //}


        }
    }
}
