using System;
namespace Group8
{
    public class Timer : IDisposable
    {
        private DateTime start;
        private string message;
        private Action<TimeSpan> del;

        public Timer(string message, Action<TimeSpan> del)
        {
            this.message = message;
            this.start = DateTime.Now;
            this.del = del;

        }
        public Timer(string message)
        {
            this.message = message;
            this.start = DateTime.Now;
        }

        public Timer(Action<TimeSpan> del)
        {
            this.message = "";
            this.start = DateTime.Now;
        }

        private bool disposed = false;
        public void Dispose()
        {
            if (!this.disposed)
            {
                disposed = true;
                TimeSpan ts = DateTime.Now - start;
                Console.Error.WriteLine(message);
                Console.Error.WriteLine(ts.TotalMilliseconds);
                del?.Invoke(ts);
            }
        }

        ~Timer()
        {
            Dispose();
        }



    }
}
