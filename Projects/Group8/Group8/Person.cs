using System;
namespace Group8
{
    public abstract class Person
    {

        private string name;
        public String Name
        {
            get { return name; }
            set { name = value; }
        }

        public virtual double Rating
        {
            get
            {
                return 4.4;
            }
        }

        protected Person(string name)
        {
            this.name = name;
        }

    }
}
