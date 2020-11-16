using System;
using System.Collections.Generic;
using System.Linq;
namespace Group8
{
    public class Instructor : Person
    {
        private List<double> evaluations;


        public override double Rating
        {
            get
            { 
                return evaluations.Average();

            }
        }

        public Instructor(string name, List<double> evaluations) : base(name)
        {
            this.evaluations = new List<double>(evaluations);
        }
    }
}
