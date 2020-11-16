using System;
namespace DelegatesAndEvents
{
    public partial class Account:Object
    {
        public event EventHandler<AccountEventArgs> signal;
        private double _balance;
        private string _name;

        public Account(string name, double balance)
        {
            _name = name;
            _balance = balance;
        }
        public double Balance
        {
            get { return _balance; }
        }
        protected virtual void OnOverdrawn(AccountEventArgs e)
        { 
            signal.Invoke(this, e); 
        }

        public void Withdraw(double amt)
        {
            _balance -= amt;
            if (_balance < 0)
                OnOverdrawn(new AccountEventArgs(_balance)); // EMIT SIGNAL
        }
    }

}