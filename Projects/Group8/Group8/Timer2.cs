using System;
namespace Group8
{
    public class Timer2 : IDisposable
    {
        private DateTime start;
        string message;
        public Timer2(string message)
        {
            this.start = DateTime.Now;
            this.message = message;
        }

        private bool disposed = false;
        public void Dispose()
        {
            if (!this.disposed)
            {
                disposed = true;
                DateTime stop = DateTime.Now;
                TimeSpan ts = stop - start;
                Console.Error.WriteLine(message);
                Console.Error.WriteLine(ts.TotalMilliseconds);

            }
        }

        ~Timer2()
        {
            Dispose();
        }



    }
}
