using SynapseModelBase.Constants;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Reflection;

namespace SynapseServices.Services.UtilsServices
{
    public class LogWriter : IDisposable
    {
        /// <summary>
        /// Pasta root dos logs
        /// </summary>
        private static string PATH = string.Empty;

        /// <summary>
        /// Nome do arquivo de log
        /// </summary>
        private const string exceptionLogFilename = "HorusExceptionLog.txt";

        /// <summary>
        /// Nome do arquivo de log
        /// </summary>
        private const string variablesLengthLogFilename = "HorusLengthLog.txt";

        /// <summary>
        /// Nome do arquivo de log
        /// </summary>
        private const string exceptionMessageLogFilename = "HorusExceptionMessageLog.txt";


        /// <summary>
        /// Pasta de logs
        /// </summary>
        private const string folderName = "Logs";

        /// <summary>
        /// Flag que identifica se está ou não escrevendo no arquivo
        /// </summary>
        private static bool isExceptionWriting = false;

        /// <summary>
        /// Flag que identifica se está ou não escrevendo no arquivo
        /// </summary>
        private static bool isLengthLogWriting = false;

        /// <summary>
        /// Flag que identifica se está ou não escrevendo no arquivo
        /// </summary>
        private static bool isExceptionMessageWriting = false;

        /// <summary>
        /// Constrói o nome do arquivo e entraga o path onde o mesmo se encontra
        /// </summary>
        /// <param name="aplicationTag"></param>
        /// <param name="filename"></param>
        /// <returns></returns>
        private string GetFullFilename(string aplicationTag, string filename)
        {
            try
            {
                if (string.IsNullOrEmpty(PATH))
                {
                    PATH = Path.GetDirectoryName(Assembly.GetEntryAssembly().Location) + "\\" + folderName + "\\" + aplicationTag + "\\";
                }

                Directory.CreateDirectory(PATH);

                string newFilename = PATH + DateTime.Today.ToString("dd-MM-yy") + "_" + filename;
                return newFilename;
            }
            catch (Exception exception)
            {
                Console.WriteLine(exception.Message);
                return "c:\\HorusLogs\\" + folderName + "\\" + aplicationTag + "\\" + DateTime.Today.ToString("dd-MM-yy") + "_" + filename;
            }
        }


        //public static void Write(Exception exception, object tAG1, object method, string tAG2)
        //{
        //    throw new NotImplementedException();
        //}


        /// <summary>
        /// Escreve as exceptions tanto em arquivo quanto em sistema de eventos
        /// </summary>
        /// <param name="exception"></param>
        /// <param name="tag"></param>
        /// <param name="method"></param>
        /// <param name="aplicationTag"></param>
        public void Write(Exception exception, string tag, string method, string aplicationTag)
        {
            try
            {
                if (!EventLog.SourceExists(aplicationTag))
                {
                    EventLog.CreateEventSource(aplicationTag, SystemConstant.SYSTEM.TITLE);
                }

                string text = DateTime.Now + " | Class Name:" + tag + " | Method: " + method + " | Exception:" + exception.Message;

                if (exception.InnerException != null
                    && !string.IsNullOrEmpty(exception.InnerException.Message))
                {
                    text += " | InnerException: " + exception.InnerException;
                }

                EventLog.WriteEntry(aplicationTag, text, EventLogEntryType.Error, 0);

                if (!isExceptionWriting)
                {
                    isExceptionWriting = true;
                    System.IO.File.AppendAllText(@"" + GetFullFilename(aplicationTag, exceptionLogFilename), text + "\n");
                    isExceptionWriting = false;
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                isExceptionWriting = false;
            }
        }

        /// <summary>
        /// Escreve as exceptions tanto em arquivo quanto em sistema de eventos
        /// </summary>
        /// <param name="exception"></param>
        /// <param name="tag"></param>
        /// <param name="method"></param>
        /// <param name="aplicationTag"></param>
        public void Write(string exception, string tag, string method, string aplicationTag)
        {
            try
            {
                if (!EventLog.SourceExists(aplicationTag))
                {
                    EventLog.CreateEventSource(aplicationTag, SystemConstant.SYSTEM.TITLE);
                }

                string text = DateTime.Now + " | Class Name:" + tag + " | Method: " + method + " | Exception:" + exception;
                EventLog.WriteEntry(aplicationTag, text, EventLogEntryType.Error, 0);

                if (!isExceptionMessageWriting)
                {
                    isExceptionMessageWriting = true;
                    System.IO.File.AppendAllText(@"" + GetFullFilename(aplicationTag, exceptionMessageLogFilename), text + "\n");
                    isExceptionMessageWriting = false;
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                isExceptionMessageWriting = false;
            }
        }

        /// <summary>
        /// Coloca no arquivo de log o tamanho das variaveis
        /// </summary>
        /// <param name="dataMap"></param>
        /// <param name="aplicationTag"></param>
        public void WriteVariablesLength(Dictionary<string, double> dataMap, string aplicationTag)
        {
            try
            {
                if(dataMap == null || dataMap.Count == 0)
                {
                    return;
                }

                string text = DateTime.Now + ": {\r\n";

                foreach (var item in dataMap)
                {
                    try
                    {
                        if (item.Value > 50)
                        {
                            text += "[" + item.Key + "=" + item.Value + "]\r\n";
                        }

                        //text += "[" + item.Key + "=" + item.Value + "]\r\n";
                    }
                    catch (Exception exception)
                    {
                        Console.WriteLine(exception.Message);
                    }
                }

                text += "}";

                if (!isLengthLogWriting)
                {
                    isLengthLogWriting = true;
                    System.IO.File.AppendAllText(@"" + GetFullFilename(aplicationTag, variablesLengthLogFilename), text + "\n");
                    isLengthLogWriting = false;
                }
            }
            catch (Exception exception)
            {
                Console.WriteLine(exception.Message);
                isLengthLogWriting = false;
            }
        }

        #region IDisposable Support
        private bool disposedValue = false; // To detect redundant calls

        protected virtual void Dispose(bool disposing)
        {
            if (!disposedValue)
            {
                if (disposing)
                {
                    // TODO: dispose managed state (managed objects).
                }

                // TODO: free unmanaged resources (unmanaged objects) and override a finalizer below.
                // TODO: set large fields to null.

                disposedValue = true;
            }
        }

        // TODO: override a finalizer only if Dispose(bool disposing) above has code to free unmanaged resources.
        // ~LogWriter() {
        //   // Do not change this code. Put cleanup code in Dispose(bool disposing) above.
        //   Dispose(false);
        // }

        // This code added to correctly implement the disposable pattern.
        public void Dispose()
        {
            // Do not change this code. Put cleanup code in Dispose(bool disposing) above.
            Dispose(true);
            // TODO: uncomment the following line if the finalizer is overridden above.
            // GC.SuppressFinalize(this);
        }
        #endregion
    }
}
