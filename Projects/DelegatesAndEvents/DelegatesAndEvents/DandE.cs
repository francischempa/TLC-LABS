using System;
using System.Collections;
using System.Collections.Generic; 
namespace DelegatesAndEvents
{
    public class DandE
    {
        public static void ForEach(IEnumerable c, Action<int> action) {
            foreach(int element in c) {
                action?.Invoke(element);
            }
        }



        public static void Main5(string[] args)
        {
            IEnumerable<int> L = new List<int>() {1,2,3,4,5,4,3,2,1 };
            ForEach(L, Console.WriteLine);

        }
    }
}
