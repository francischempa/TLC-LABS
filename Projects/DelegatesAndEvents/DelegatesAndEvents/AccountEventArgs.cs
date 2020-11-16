using System;
namespace DelegatesAndEvents
{
    public class AccountEventArgs : EventArgs
    {
        private double _balance;

        public AccountEventArgs(double balance)
        {
            _balance = balance;
        }
        public double Balance
        {
            get { return _balance; }
        }
    }
}
