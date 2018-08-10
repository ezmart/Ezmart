using Ionic.Zip;
using LiveCharts;
using Newtonsoft.Json;
using SynapseModelBase.Constants;
using SynapseModelBase.Poco.V1;
using SynapseModelBase.TemplatePoco;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Globalization;
using System.IO;
using System.IO.Compression;
using System.Linq;
using System.Net;
using System.Net.Mail;
using System.Text;
using System.Windows.Media.Imaging;

namespace SynapseServices.Services.UtilsServices
{
    public class UtilService : IDisposable
    {
        /// <summary>
        /// Tag
        /// </summary>
        private const string TAG = "UtilService";

        /// <summary>
        /// Valida o campo de Factor
        /// Os possiveis resultados sao:
        /// Em caso de falha: Vazio
        /// No caso de sucesso: operador + valor | Exemplo: +5.63
        /// </summary>
        /// <param name="dataToBeValidated"></param>
        /// <returns></returns>
        public string ValidateThirdPartyFactorField(string dataToBeValidated)
        {
            try
            {
                string response = string.Empty;

                if (string.IsNullOrEmpty(dataToBeValidated))
                {
                    dataToBeValidated = "+0";
                }

                // caso o conteudo seja diferente de vazio, verifica se o mesmo eh valido

                /// Para o conteudo ser valido, o primeiro valor deve ser o 'operador' e o segundo o 'valor'
                /// Exemplo: *10
                /// Onde: 
                /// * eh o operador
                /// 10 eh o valor
                /// O que resulta em, caso o valor lido seja 20, o mesmo sera multiplicado por 10, resultando em 200
                /// Segue os operadores aceitos:
                /// * = Multiplicacao
                /// *- = Inversão
                /// / = Divisao
                /// - = Subtracao
                /// + = Soma
                /// NÃO PODE SER ACEITA DIVISÃO POR ZERO!!


                // verifica o operador na primeira posicao
                char op = dataToBeValidated.ElementAtOrDefault(0);
                string auxData = string.Empty;

                /// se o valor do operador for diferente, adiciona o + como padrao
                if (!op.Equals('+') && !op.Equals('-') && !op.Equals('/') && !op.Equals('*'))
                {
                    op = '+';
                    auxData = op + dataToBeValidated;
                }
                else
                {
                    auxData = dataToBeValidated;
                }

                // visto que agora o operador existe, quebra o resultado logo apos o operador
                string auxValue = auxData.Substring(1);

                // troca os sinais de , por .
                auxValue = auxValue.Replace(",", ".");

                // caso encontre qualquer tipo de operador, remove

                if (op == '+' || op == '-') {
                    auxValue = RemoveSpecialCharacters(auxValue);

                }else
                {
                    auxValue = RemoveSpecialCharactersAllowingSub(auxValue);

                }

                // remove os espacos
                auxValue = auxValue.Trim();

                // transforma o valor em double
                double value = 0;
                double.TryParse(auxValue, NumberStyles.Any, CultureInfo.InvariantCulture, out value);

                response = op + value.ToString(CultureInfo.InvariantCulture);

                //impede divisão por zero
                if (response.Equals("/0"))
                {
                    response = "+0";
                }

                return response;
            }
            catch (Exception exception)
            {
                Console.WriteLine(exception.Message);
                return string.Empty;
            }
        }

        /// <summary>
        /// Remove todos os caracteres especiais, menos a virgula e subtração
        /// </summary>
        /// <param name="str"></param>
        /// <returns></returns>
        public string RemoveSpecialCharactersAllowingSub(string str)
        {
            try
            {
                StringBuilder sb = new StringBuilder();
                foreach (char c in str)
                {
                    try
                    {
                        if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || c == '.' || c == '-')
                        {
                            sb.Append(c);
                        }
                    }
                    catch (Exception exception)
                    {
                        Console.WriteLine(exception.Message);
                    }
                }
                return sb.ToString();
            }
            catch (Exception exception)
            {
                Console.WriteLine(exception.Message);
                return string.Empty;
            }
        }

        /// <summary>
        /// Remove todos os caracteres especiais, menos a virgula
        /// </summary>
        /// <param name="str"></param>
        /// <returns></returns>
        public string RemoveSpecialCharacters(string str)
        {
            try
            {
                StringBuilder sb = new StringBuilder();
                foreach (char c in str)
                {
                    try
                    {
                        if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || c == '.' )
                        {
                            sb.Append(c);
                        }
                    }
                    catch (Exception exception)
                    {
                        Console.WriteLine(exception.Message);
                    }
                }
                return sb.ToString();
            }
            catch (Exception exception)
            {
                Console.WriteLine(exception.Message);
                return string.Empty;
            }
        }

        /// <summary>
        /// A conversao se faz da seguinte forma:
        /// 1. Realiza o split com base no ponto final.
        /// 1.1. Se o tamanho do split for diferente de 3, retorna erro.
        /// 2. A primeira posicao do vetor eh o item com maior peso, e a ultima, por consequencia, menor peso.
        /// 2.1. Cada peso eh o equivalente a elevar 10 pela posicao do vetor (de forma invertida).
        /// 2.2. Exemplo: Versao -> 2.3.4
        /// - 3 posicoes no vetor
        /// - Primeira posicao: 2 * 10^2 (10 elevado a 2)
        /// - Segunda posicao:  3 * 10^1 (10 elevado a 1)
        /// - Terceira posicao: 4 * 10^0 (10 elevado a 0)
        /// 3. Com estes resultados em maos, faz a soma e retorna o valor
        /// 3.1. Exemplo: 200 + 30 + 4 = 234
        /// </summary>
        /// <param name="systemVersion"></param>
        /// <returns></returns>
        public double GetConvertedSystemVersion(string systemVersion)
        {
            string method = "GetConvertedSystemVersion";
            double convertedValue = -1;
            try
            {
                if (string.IsNullOrEmpty(systemVersion))
                {
                    return convertedValue;
                }

                var splittedValue = systemVersion.Split('.');

                if (splittedValue.Length != 3)
                {
                    return convertedValue;
                }

                double i = splittedValue.Length - 1;
                double auxValue = 0;
                foreach (var item in splittedValue)
                {
                    try
                    {
                        //auxValue += double.Parse(item.Replace(",", ".")) * Math.Pow(10, i);
                        //i -= 1;
                        double auxDouble = 0;
                        if (double.TryParse(item.Replace(",", "."), NumberStyles.Any, CultureInfo.InvariantCulture, out auxDouble))
                        {
                            auxValue += auxDouble * Math.Pow(10, i);
                        }
                        i -= 1;
                    }
                    catch (Exception exception)
                    {
                        Console.WriteLine(exception.Message);
                        return convertedValue;
                    }
                }

                if (auxValue != 0 && auxValue >= 100)
                {
                    convertedValue = auxValue;
                }

                return Math.Round(convertedValue, 0);
            }
            catch (Exception exception)
            {
                new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG); Console.WriteLine(exception.Message);
                return convertedValue;
            }
        }



        /// <summary>
        /// Prepara os dados para analise do grafico
        /// </summary>
        /// <param name="conditionList"></param>
        /// <param name="dataloggerByMeasure"></param>
        /// <returns></returns>
        public Dictionary<string, object> PrepareAnalysisDataChart(ObservableCollection<AnalysisModel> conditionList,
            Dictionary<Guid, List<DataloggerModel>> dataloggerByMeasure)
        {
            string method = "PrepareAnalysisDataChart";
            try
            {
                var responseData = new Dictionary<string, object>();

                // Caso a lista de condicoes esteja vazia, nao faz nada.
                if (conditionList == null || conditionList.Count == 0
                    || dataloggerByMeasure == null || dataloggerByMeasure.Count == 0)
                {
                    return null;
                }

                var insideCondition = new ChartValues<double>();
                var outsideCondition = new ChartValues<double>();
                var labelsAnalysis = new List<string>();
                var analysisList = new List<AnalysisModel>();

                //Itera as condições
                foreach (var item in conditionList)
                {
                    try
                    {
                        if (dataloggerByMeasure.ContainsKey(item.EquipmentDataId))
                        {
                            var dataloggerData = dataloggerByMeasure[item.EquipmentDataId];

                            if (dataloggerData != null && dataloggerData.Count != 0)
                            {
                                double amountOfDataThatMatchesTheCondition = 0;

                                switch (item.Condition)
                                {
                                    case ">":
                                        amountOfDataThatMatchesTheCondition = dataloggerData.Where(x => x.Value > item.Value).Distinct().ToList().Count;
                                        insideCondition.Add(Math.Round((amountOfDataThatMatchesTheCondition / dataloggerData.Count) * 100, 2));
                                        outsideCondition.Add(Math.Round(((dataloggerData.Count - amountOfDataThatMatchesTheCondition) / dataloggerData.Count) * 100, 2));
                                        break;

                                    case "<":
                                        amountOfDataThatMatchesTheCondition = dataloggerData.Where(x => x.Value < item.Value).Distinct().ToList().Count;
                                        insideCondition.Add(Math.Round((amountOfDataThatMatchesTheCondition / dataloggerData.Count) * 100, 2));
                                        outsideCondition.Add(Math.Round(((dataloggerData.Count - amountOfDataThatMatchesTheCondition) / dataloggerData.Count) * 100, 2));
                                        break;

                                    case ">=":
                                        amountOfDataThatMatchesTheCondition = dataloggerData.Where(x => x.Value >= item.Value).Distinct().ToList().Count;
                                        insideCondition.Add(Math.Round((amountOfDataThatMatchesTheCondition / dataloggerData.Count) * 100, 2));
                                        outsideCondition.Add(Math.Round(((dataloggerData.Count - amountOfDataThatMatchesTheCondition) / dataloggerData.Count) * 100, 2));
                                        break;

                                    case "<=":
                                        amountOfDataThatMatchesTheCondition = dataloggerData.Where(x => x.Value <= item.Value).Distinct().ToList().Count;
                                        insideCondition.Add(Math.Round((amountOfDataThatMatchesTheCondition / dataloggerData.Count) * 100, 2));
                                        outsideCondition.Add(Math.Round(((dataloggerData.Count - amountOfDataThatMatchesTheCondition) / dataloggerData.Count) * 100, 2));
                                        break;

                                    case "=":
                                        amountOfDataThatMatchesTheCondition = dataloggerData.Where(x => x.Value == item.Value).Distinct().ToList().Count;
                                        insideCondition.Add(Math.Round((amountOfDataThatMatchesTheCondition / dataloggerData.Count) * 100, 2));
                                        outsideCondition.Add(Math.Round(((dataloggerData.Count - amountOfDataThatMatchesTheCondition) / dataloggerData.Count) * 100, 2));
                                        break;

                                    default:
                                        break;
                                }

                                /// Aloca as informacoes na lista em _analysisList para ser reutilizada em
                                /// caso do usuario tentar gerar o pdf das informacoes
                                /// 
                                double ticks = 0;
                                try
                                {
                                    DateTime l = dataloggerData.Where(x => x.EquipmentDataId.Equals(item.EquipmentDataId)).OrderBy(x => x.DateTime).LastOrDefault().DateTime;
                                    DateTime f = dataloggerData.Where(x => x.EquipmentDataId.Equals(item.EquipmentDataId)).OrderBy(x => x.DateTime).FirstOrDefault().DateTime;
                                    var timeIn = l.Subtract(f);
                                    if (amountOfDataThatMatchesTheCondition > 0)
                                    {
                                        ticks = (timeIn.TotalMilliseconds * (amountOfDataThatMatchesTheCondition / dataloggerData.Count));
                                    }
                                    else
                                    {
                                        ticks = 0;
                                    }
                                }
                                catch
                                {

                                }

                                analysisList.Add(new AnalysisModel()
                                {
                                    TimeIn = TimeSpan.FromMilliseconds((long)ticks),
                                    Condition = item.ScreenName + "|" + item.MeasureName + " " + item.Condition + " " + item.Value,
                                    Affirmative = Convert.ToInt32(amountOfDataThatMatchesTheCondition),
                                    Negative = Convert.ToInt32(dataloggerData.Count - amountOfDataThatMatchesTheCondition),
                                });
                                labelsAnalysis.Add(item.ScreenName + "|" + item.MeasureName + " " + item.Condition + " " + item.Value);
                            }
                        }
                    }
                    catch (Exception exception)
                    {
                        Console.WriteLine(exception.Message);
                    }
                }

                // controi o pacote de resposta
                responseData.Add("ANALYSIS_LIST", analysisList);
                responseData.Add("LABEL_ANALYSIS", labelsAnalysis);
                responseData.Add("INSIDE_CONDITION", insideCondition);
                responseData.Add("OUTSIDE_CONDITION", outsideCondition);

                return responseData;
            }
            catch (Exception exception)
            {
                new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG); Console.WriteLine(exception.Message);
                return null;
            }
        }


        /// <summary>
        /// Transforma o arquivo em um array de bytes
        /// </summary>
        /// <param name="fileName"></param>
        /// <returns></returns>
        public byte[] FileToByteArray(string fileName)
        {
            string method = "FileToByteArray";
            try
            {
                byte[] buff = null;
                FileStream fs = new FileStream(fileName,
                                               FileMode.Open,
                                               FileAccess.Read);
                BinaryReader br = new BinaryReader(fs);
                long numBytes = new FileInfo(fileName).Length;
                buff = br.ReadBytes((int)numBytes);
                return buff;
            }
            catch (Exception exception)
            {
                new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG); Console.WriteLine(exception.Message);
                return null;
            }
        }



        /// <summary>
        /// Rotina que envia o email
        /// </summary>
        /// <param name="systemEmail"></param>
        /// <param name="subject"></param>
        /// <param name="body"></param>
        /// <param name="sendEmailTo"></param>
        /// <param name="attachment"></param>
        public bool SendEmail(SystemEmailData systemEmail, string subject, string body, List<AddressData> recipientList, Attachment attachment = null)
        {
            string method = "SendEmail";
            try
            {
                bool result = false;

                if (recipientList == null || recipientList.Count == 0)
                {
                    return result;
                }

                using (var client = new SmtpClient(systemEmail.SmtpHost, systemEmail.SmtpPort))
                {
                    client.Credentials = new NetworkCredential(systemEmail.Email, systemEmail.Password);
                    //client.UseDefaultCredentials = false;
                    client.EnableSsl = systemEmail.IsSslEnabled;

                    MailMessage mail = new MailMessage();
                    mail.From = new MailAddress(systemEmail.Email);
                    mail.Subject = subject;
                    mail.Body = body;

                    // cria a lista dos usuarios que irao receber o email
                    foreach (var recipient in recipientList)
                    {
                        try
                        {
                            // garante que eh do tipo email para nao dar merda
                            if (recipient.Type.Equals(SystemConstant.DELIVERY.TYPE.EMAIL))
                            {
                                mail.To.Add(new MailAddress(recipient.Address));
                            }
                        }
                        catch (Exception exception)
                        {
                            new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG); Console.WriteLine(exception.Message);
                        }
                    }

                    // checa se contem anexo
                    if (attachment != null)
                    {
                        mail.Attachments.Add(attachment);
                    }

                    client.Send(mail);
                    result = true;
                }

                return result;
            }
            catch (Exception exception)
            {
                new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG); Console.WriteLine(exception.Message);
                return false;
            }
            finally
            {
                /// Aguarda 03 segundos antes de enviar o proximo email para evitar com que o servidor
                /// bloqueie a conta
                System.Threading.Tasks.Task.Delay(3000);
            }
        }

        /// <summary>
        /// Separa as medidas das telas em equipamentos e equipmentData
        /// </summary>
        /// <param name="contentScreenAndMeasures"></param>
        /// <returns></returns>
        public Dictionary<Guid, List<Guid>> SplitScreenAndMeasuresIntoEquipmentMap(Dictionary<Guid, List<ComponentGauge>> contentScreenAndMeasures)
        {
            string method = "SplitScreenAndMeasuresIntoEquipmentMap";
            try
            {
                var dataMap = new Dictionary<Guid, List<Guid>>();

                foreach (var screenMeasures in contentScreenAndMeasures)
                {
                    try
                    {
                        if (screenMeasures.Value != null && screenMeasures.Value.Count != 0)
                        {
                            foreach (var measure in screenMeasures.Value)
                            {
                                try
                                {
                                    // O id que eh utilizado nas tabelas de datalogger como referencia eh que contem PortName

                                    if (!dataMap.ContainsKey(measure.EquipmentDataReference.EquipmentId))
                                    {
                                        dataMap.Add(measure.EquipmentDataReference.EquipmentId, new List<Guid>());
                                    }

                                    if (measure.EquipmentDataReference.Owner.Equals(SystemConstant.SYSTEM.TSDA))
                                    {
                                        var equipmentDataReference = measure.GaugeEquipmentDataList.FirstOrDefault(x => x.EquipmentData.OidName.Equals(SystemConstant.MIB.TSDA.DEFAULT_COMMON_OID_NAMES.PORT_NAME));

                                        dataMap[measure.EquipmentDataReference.EquipmentId].Add(equipmentDataReference.EquipmentDataId);
                                    }
                                    else
                                    {
                                        var equipmentDataReference = measure.GaugeEquipmentDataList.FirstOrDefault(x => x.EquipmentData.OidName.Equals(SystemConstant.MIB.THIRD_PARTY.DEFAULT_COMMON_OID_NAMES.VALUE));

                                        dataMap[measure.EquipmentDataReference.EquipmentId].Add(equipmentDataReference.EquipmentDataId);
                                    }
                                }
                                catch (Exception exception)
                                {
                                    Console.WriteLine(exception.Message);
                                }
                            }

                        }
                    }
                    catch (Exception exception)
                    {
                        Console.WriteLine(exception.Message);
                    }
                }

                return dataMap;
            }
            catch (Exception exception)
            {
                new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG); Console.WriteLine(exception.Message);
                return null;
            }
        }


        /// <summary>
        /// Converte a data e hora para os padroes do mysql
        /// </summary>
        /// <param name="dateTimeToBeConverted"></param>
        /// <returns></returns>
        public string ConvertDateTimeToMySqlDefaults(DateTime? dateTimeToBeConverted)
        {
            string method = "ConvertDateTimeToMySqlDefaults";
            try
            {
                if (dateTimeToBeConverted == null)
                {
                    return null;
                }
                return string.Format("{0:yyyy-MM-dd HH:mm:ss}", dateTimeToBeConverted);
            }
            catch (Exception exception)
            {
                new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG); Console.WriteLine(exception.Message);
                return string.Empty;
            }
        }

        /// <summary>
        /// Retorna a string do ip no formato que o datalogger necessita.
        /// Exemplo:
        /// Entrada: 192.168.1.94
        /// Saída: 192_168_1_94
        /// </summary>
        /// <param name="equipmentIp"></param>
        /// <returns></returns>
        public string FormatEquipmentIpToFitDAOClasses(string equipmentIp)
        {
            string method = "FormatEquipmentIpToFitDAOClasses";
            try
            {
                return equipmentIp.Replace(".", "_");
            }
            catch (Exception exception)
            {
                new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG); Console.WriteLine(exception.Message);
                return string.Empty;
            }
        }

        /// <summary>
        /// Comprime a string
        /// </summary>
        /// <param name="stringToBeCompressed"></param>
        /// <returns></returns>
        public byte[] CompressStringContent(string stringToBeCompressed)
        {
            string method = "CompressStringContent";
            try
            {
                byte[] compressed = null;

                using (var outStream = new MemoryStream())
                {
                    using (var stream = new GZipStream(outStream, CompressionMode.Compress))
                    {
                        using (var mStream = new MemoryStream(Encoding.UTF8.GetBytes(stringToBeCompressed)))
                        {
                            mStream.CopyTo(stream);
                        }
                    }

                    compressed = outStream.ToArray();
                }

                return compressed;
            }
            catch (Exception exception)
            {
                new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG); Console.WriteLine(exception.Message);
                return null;
            }
        }

        /// <summary>
        /// Retorna uma lista com a lista de entidades no tamanho especificado
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <param name="list"></param>
        /// <param name="size"></param>
        /// <returns></returns>
        public List<List<T>> SplitRequestEntitiesInSmallChunks<T>(List<T> list, int size)
        {
            string method = "SplitRequestObjects";
            try
            {
                List<List<T>> listToBeReturned = new List<List<T>>();

                for (int i = 0; i < list.Count; i += size)
                {
                    listToBeReturned.Add(list.GetRange(i, Math.Min(size, list.Count - i)));
                }

                return listToBeReturned;
            }
            catch (Exception exception)
            {
                new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG); Console.WriteLine(exception.Message);
                return null;
            }
        }

        /// <summary>
        /// Extrai o conteudo em string
        /// </summary>
        /// <param name="compressedContent"></param>
        /// <returns></returns>
        public string DecompressToString(byte[] compressedContent)
        {
            string method = "DecompressToString";
            try
            {
                using (var inStream = new MemoryStream(compressedContent))
                {
                    using (var bigStream = new GZipStream(inStream, CompressionMode.Decompress))
                    {
                        string output = string.Empty;

                        using (var bigStreamOut = new MemoryStream())
                        {
                            bigStream.CopyTo(bigStreamOut);
                            output = Encoding.UTF8.GetString(bigStreamOut.ToArray());
                        }
                        return output;
                    }
                }
            }
            catch (Exception exception)
            {
                new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG); Console.WriteLine(exception.Message);
                return null;
            }
        }


        /// <summary>
        /// Extrai o conteudo e substitui os arquivos existentes
        /// </summary>
        /// <param name="path"></param>
        /// <param name="module"></param>
        /// <returns></returns>
        public void DecompressAndReplaceExistingFiles(string path, string module)
        {
            string method = "DecompressAndReplaceExistingFiles";
            try
            {
                string filename = string.Empty;
                switch (module)
                {
                    case SystemConstant.MODULES.SERVER.NAME:
                        filename = SystemConstant.SYSTEM.UPDATED_MODULES.MODULES.SERVER;
                        break;
                    case SystemConstant.MODULES.VIEWER.NAME:
                        filename = SystemConstant.SYSTEM.UPDATED_MODULES.MODULES.VIEWER;
                        break;
                    case SystemConstant.MODULES.STUDIO.NAME:
                        filename = SystemConstant.SYSTEM.UPDATED_MODULES.MODULES.STUDIO;
                        break;
                    case SystemConstant.MODULES.CONNECTION_MANAGER.NAME:
                        filename = SystemConstant.SYSTEM.UPDATED_MODULES.MODULES.CONNECTION_MANAGER;
                        break;
                    case SystemConstant.MODULES.REPORT_MANAGER.NAME:
                        filename = SystemConstant.SYSTEM.UPDATED_MODULES.MODULES.REPORT_MANAGER;
                        break;
                    case SystemConstant.MODULES.UPDATER.NAME:
                        filename = SystemConstant.SYSTEM.UPDATED_MODULES.MODULES.UPDATER;
                        break;
                    default: break;
                }

                if (string.IsNullOrEmpty(filename))
                {
                    return;
                }

                string zipPath = Path.Combine(path, filename);
                using (ZipFile zipContent = ZipFile.Read(zipPath))
                {
                    foreach (ZipEntry entry in zipContent)
                    {
                        try
                        {
                            entry.LastModified = DateTime.Now;
                            entry.Extract(path, ExtractExistingFileAction.OverwriteSilently);
                        }
                        catch (Exception exception)
                        {
                            new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG); Console.WriteLine(exception.Message);
                        }
                    }
                }
                File.Delete(zipPath);
            }
            catch (Exception exception)
            {
                new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG); Console.WriteLine(exception.Message);
            }
        }


        ///// <summary>
        ///// Comprime uma string
        ///// </summary>
        ///// <param name="source"></param>
        ///// <returns></returns>
        //public string CompressStringContent(string stringToBeCompressed)
        //{
        //    string method = "CompressStringContent";
        //    try
        //    {
        //        using (MemoryStream memoryStream = new MemoryStream())
        //        {
        //            byte[] buffer = Encoding.UTF8.GetBytes(stringToBeCompressed);
        //            using (GZipStream zip = new GZipStream(memoryStream, CompressionMode.Compress, true))
        //            {
        //                zip.Write(buffer, 0, buffer.Length);
        //            }

        //            memoryStream.Position = 0;
        //            MemoryStream outStream = new MemoryStream();

        //            byte[] compressed = new byte[memoryStream.Length];
        //            memoryStream.Read(compressed, 0, compressed.Length);

        //            byte[] gzBuffer = new byte[compressed.Length + 4];
        //            System.Buffer.BlockCopy(compressed, 0, gzBuffer, 4, compressed.Length);
        //            System.Buffer.BlockCopy(BitConverter.GetBytes(buffer.Length), 0, gzBuffer, 0, 4);
        //            return Convert.ToBase64String(gzBuffer);
        //        }
        //    }
        //    catch (Exception exception)
        //    {
        //        new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG); Console.WriteLine(exception.Message);
        //        return string.Empty;
        //    }
        //}

        ///// <summary>
        ///// Descomprime um conteudo em string
        ///// </summary>
        ///// <param name="compressedText"></param>
        ///// <returns></returns>
        //public string DecompressToString(string compressedText)
        //{
        //    string method = "DecompressToString";
        //    try
        //    {
        //        using (MemoryStream ms = new MemoryStream())
        //        {
        //            byte[] gzBuffer = Convert.FromBase64String(compressedText);
        //            int msgLength = BitConverter.ToInt32(gzBuffer, 0);
        //            ms.Write(gzBuffer, 4, gzBuffer.Length - 4);

        //            byte[] buffer = new byte[msgLength];

        //            ms.Position = 0;
        //            using (GZipStream zip = new GZipStream(ms, CompressionMode.Decompress))
        //            {
        //                zip.Read(buffer, 0, buffer.Length);
        //            }

        //            return Encoding.UTF8.GetString(buffer);
        //        }
        //    }
        //    catch (Exception exception)
        //    {
        //        new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG); Console.WriteLine(exception.Message);
        //        return string.Empty;
        //    }
        //}

        /// <summary>
        /// Converte o texto em hexadecimal
        /// </summary>
        /// <param name="source"></param>
        /// <returns></returns>
        public string ConvertStringToHex(string source)
        {
            string method = "ConvertStringToHex";
            try
            {
                byte[] byteArray = Encoding.BigEndianUnicode.GetBytes(source);
                string result = BitConverter.ToString(byteArray);
                result = result.Replace("-", "");
                return result;
            }
            catch (Exception exception)
            {
                new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG);
                Console.WriteLine(exception.Message);
                return string.Empty;
            }
        }

        /// <summary>
        /// Valida o endereço de email
        /// </summary>
        /// <param name="address"></param>
        /// <returns></returns>
        public bool ValidateEmailAddress(string address)
        {
            string method = "ValidateEmailAddress";
            try
            {
                if (string.IsNullOrEmpty(address)
                        || !address.Contains("@")
                        || !address.Contains(".")
                        || address.StartsWith("@")
                        || address.EndsWith("@")
                        || address.StartsWith(".")
                        || address.EndsWith("."))
                {
                    return false;
                }

                var numberOfAts = address.Split('@');
                if (numberOfAts.Length != 2)
                {
                    return false;
                }

                return true;
            }
            catch (Exception exception)
            {
                new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG);
                Console.WriteLine(exception.Message);
                return false;
            }
        }

        /// <summary>
        /// Valida o endereço de sms
        /// </summary>
        /// <param name="address"></param>
        /// <returns></returns>
        public bool ValidateSmsAddress(string address)
        {
            string method = "ValidateSmsAddress";
            try
            {
                long parsedValue = 0;
                if (string.IsNullOrEmpty(address)
                    || !long.TryParse(address, out parsedValue)
                    || parsedValue == 0)
                {
                    return false;
                }

                return true;
            }
            catch (Exception exception)
            {
                new LogWriter().Write(exception,
                    TAG, method, BaseService.APPLICATION_TAG);
                Console.WriteLine(exception.Message);
                return false;
            }
        }

        /// <summary>
        /// Monta o valor + unidade das medidas
        /// </summary>
        /// <param name="portType"></param>
        /// <param name="dataList"></param>
        /// <returns></returns>
        public string BuildMeasureValue(string portType, List<EquipmentData> dataList)
        {
            string response = string.Empty;
            string method = "BuildMeasureValue";
            try
            {
                // METERING
                if (portType.Equals(SystemConstant.MIB.TSDA.TYPE.METERING.NAME))
                {
                    string value = dataList
                        .FirstOrDefault(x => x.OidName.Equals(SystemConstant.MIB.TSDA.DEFAULT_COMMON_OID_NAMES.VALUE)).Value;

                    string unit = dataList
                        .FirstOrDefault(x => x.OidName.Equals(SystemConstant.MIB.TSDA.TYPE.METERING.DEFAULT_FIELD_NAMES.UNIT)).Value;

                    // garante o conteúdo
                    if (string.IsNullOrEmpty(value))
                    {
                        value = string.Empty;
                    }
                    // garante o conteúdo
                    if (string.IsNullOrEmpty(unit))
                    {
                        unit = string.Empty;
                    }
                    response = value + " " + unit;
                }
                // STATUS
                else if (portType.Equals(SystemConstant.MIB.TSDA.TYPE.STATUS.NAME))
                {
                    string value = dataList
                            .FirstOrDefault(x => x.OidName.Equals(SystemConstant.MIB.TSDA.DEFAULT_COMMON_OID_NAMES.VALUE)).Value;

                    // garante o conteúdo
                    if (string.IsNullOrEmpty(value))
                    {
                        value = "0";
                    }

                    string description = string.Empty;
                    if (value.Equals("0"))
                    {
                        description = dataList
                            .FirstOrDefault(x => x.OidName.Equals(SystemConstant.MIB.TSDA.TYPE.STATUS.DEFAULT_FIELD_NAMES.OFF_DESCRIPTION)).Value;
                    }
                    else
                    {
                        description = dataList
                            .FirstOrDefault(x => x.OidName.Equals(SystemConstant.MIB.TSDA.TYPE.STATUS.DEFAULT_FIELD_NAMES.ON_DESCRIPTION)).Value;
                    }

                    response = value + " | " + description;
                }
                //PING
                else if (portType.Equals(SystemConstant.MIB.TSDA.TYPE.CUSTOM.PING.NAME))
                {
                    string value = dataList
                            .FirstOrDefault(x => x.OidName.Equals(SystemConstant.MIB.TSDA.DEFAULT_COMMON_OID_NAMES.ALARM_STATE)).Value;

                    // garante o conteúdo
                    if (string.IsNullOrEmpty(value))
                    {
                        value = "0";
                    }

                    string description = string.Empty;
                    if (value.Equals("0"))
                    {
                        description = "OK";
                    }
                    else
                    {
                        description = "ALARM";
                    }

                    response = value + " | " + description;
                }
                else if (portType.Equals(SystemConstant.MIB.TSDA.TYPE.CUSTOM.SCRIPT.NAME))
                {
                    string value = dataList
                            .FirstOrDefault(x => x.OidName.Equals(SystemConstant.MIB.TSDA.TYPE.CUSTOM.SCRIPT.DEFAULT_FIELD_NAMES.ANSWERED)).Value;

                    // garante o conteúdo
                    if (string.IsNullOrEmpty(value))
                    {
                        value = "0";
                    }

                    string description = string.Empty;
                    if (value.Equals("0"))
                    {
                        description = "OK";
                    }
                    else
                    {
                        description = "ALARM";
                    }

                    response = value + " | " + description;
                }
                //Command
                else if (portType.Equals(SystemConstant.MIB.TSDA.TYPE.COMMAND.NAME))
                {
                    string value = dataList
                            .FirstOrDefault(x => x.OidName.Equals(SystemConstant.MIB.TSDA.DEFAULT_COMMON_OID_NAMES.VALUE)).Value;

                    // garante o conteúdo
                    if (string.IsNullOrEmpty(value))
                    {
                        value = "0";
                    }

                    string description = string.Empty;
                    if (value.Equals("0"))
                    {
                        description = "OFF";
                    }
                    else
                    {
                        description = "ON";
                    }

                    response = value + " | " + description;
                }
                // VIRTUAL
                else if (portType.Equals(SystemConstant.MIB.TSDA.TYPE.VIRTUAL.NAME))
                {
                    string measureType = dataList
                        .FirstOrDefault(x => x.OidName.Equals(SystemConstant.MIB.TSDA.TYPE.VIRTUAL.DEFAULT_FIELD_NAMES.TYPE)).Value;

                    // Padrão é 0 - status
                    if (string.IsNullOrEmpty(measureType))
                    {
                        measureType = "0";
                    }

                    // Valor 0 = STATUS
                    if (measureType.Equals("0"))
                    {
                        string value = dataList
                            .FirstOrDefault(x => x.OidName.Equals(SystemConstant.MIB.TSDA.DEFAULT_COMMON_OID_NAMES.VALUE)).Value;

                        // garante o conteúdo
                        if (string.IsNullOrEmpty(value))
                        {
                            value = "0";
                        }

                        string description = string.Empty;
                        if (value.Equals("0"))
                        {
                            description = dataList
                                .FirstOrDefault(x => x.OidName.Equals(SystemConstant.MIB.TSDA.TYPE.VIRTUAL.DEFAULT_FIELD_NAMES.DESCRIPTION_ZERO)).Value;
                        }
                        else if (value.Equals("1"))
                        {
                            description = dataList
                                .FirstOrDefault(x => x.OidName.Equals(SystemConstant.MIB.TSDA.TYPE.VIRTUAL.DEFAULT_FIELD_NAMES.DESCRIPTION_ONE)).Value;
                        }
                        else if (value.Equals("2"))
                        {
                            description = dataList
                                .FirstOrDefault(x => x.OidName.Equals(SystemConstant.MIB.TSDA.TYPE.VIRTUAL.DEFAULT_FIELD_NAMES.DESCRIPTION_TWO)).Value;
                        }
                        else if (value.Equals("3"))
                        {
                            description = dataList
                                .FirstOrDefault(x => x.OidName.Equals(SystemConstant.MIB.TSDA.TYPE.VIRTUAL.DEFAULT_FIELD_NAMES.DESCRIPTION_THREE)).Value;
                        }
                        else if (value.Equals("4"))
                        {
                            description = dataList
                                .FirstOrDefault(x => x.OidName.Equals(SystemConstant.MIB.TSDA.TYPE.VIRTUAL.DEFAULT_FIELD_NAMES.DESCRIPTION_FOUR)).Value;
                        }
                        else if (value.Equals("5"))
                        {
                            description = dataList
                                .FirstOrDefault(x => x.OidName.Equals(SystemConstant.MIB.TSDA.TYPE.VIRTUAL.DEFAULT_FIELD_NAMES.DESCRIPTION_FIVE)).Value;
                        }

                        response = value + " | " + description;
                    }
                    // Valor 1 = METERING
                    else
                    {
                        string value = dataList
                            .FirstOrDefault(x => x.OidName.Equals(SystemConstant.MIB.TSDA.DEFAULT_COMMON_OID_NAMES.VALUE)).Value;

                        string unit = dataList
                            .FirstOrDefault(x => x.OidName.Equals(SystemConstant.MIB.TSDA.TYPE.METERING.DEFAULT_FIELD_NAMES.UNIT)).Value;

                        // garante o conteúdo
                        if (string.IsNullOrEmpty(value))
                        {
                            value = string.Empty;
                        }
                        // garante o conteúdo
                        if (string.IsNullOrEmpty(unit))
                        {
                            unit = string.Empty;
                        }
                        response = value + " " + unit;
                    }


                }
            }
            catch (Exception exception)
            {
                new LogWriter().Write(exception,
                    TAG, method, BaseService.APPLICATION_TAG);
            }

            return response;
        }

        /// <summary>
        /// Descobre o tipo de alarme de acordo com o índice e tipo de porta
        /// </summary>
        /// <param name="value"></param>
        /// <param name="portType"></param>
        /// <returns></returns>
        public string DiscoverTypeOfAlarm(int value, string portType)
        {
            string response = string.Empty;
            string method = "DiscoverTypeOfAlarm";
            try
            {
                // METERING
                if (portType.Equals(SystemConstant.MIB.TSDA.TYPE.METERING.NAME))
                {
                    if (value == (int)SystemConstant.ALARM.MEASURE.COMMON.VALUES.NORMAL)
                    {
                        response = SystemConstant.ALARM.MEASURE.COMMON.VALUES.NORMAL.ToString();
                    }
                    else if (value == (int)SystemConstant.ALARM.MEASURE.TYPE.METERING.VALUES.WARNING_LOW)
                    {
                        response = SystemConstant.ALARM.MEASURE.TYPE.METERING.VALUES.WARNING_LOW.ToString();
                    }
                    else if (value == (int)SystemConstant.ALARM.MEASURE.TYPE.METERING.VALUES.WARNING_HIGH)
                    {
                        response = SystemConstant.ALARM.MEASURE.TYPE.METERING.VALUES.WARNING_HIGH.ToString();
                    }
                    else if (value == (int)SystemConstant.ALARM.MEASURE.TYPE.METERING.VALUES.ALARM_LOW)
                    {
                        response = SystemConstant.ALARM.MEASURE.TYPE.METERING.VALUES.ALARM_LOW.ToString();
                    }
                    else if (value == (int)SystemConstant.ALARM.MEASURE.TYPE.METERING.VALUES.ALARM_HIGH)
                    {
                        response = SystemConstant.ALARM.MEASURE.TYPE.METERING.VALUES.ALARM_HIGH.ToString();
                    }
                    else if (value == (int)SystemConstant.ALARM.MEASURE.COMMON.VALUES.ACKNOWLEDGED)
                    {
                        response = SystemConstant.ALARM.MEASURE.COMMON.VALUES.ACKNOWLEDGED.ToString();
                    }
                }
                // STATUS
                else if (portType.Equals(SystemConstant.MIB.TSDA.TYPE.STATUS.NAME))
                {
                    if (value == (int)SystemConstant.ALARM.MEASURE.COMMON.VALUES.NORMAL)
                    {
                        response = SystemConstant.ALARM.MEASURE.COMMON.VALUES.NORMAL.ToString();
                    }
                    else if (value == (int)SystemConstant.ALARM.MEASURE.TYPE.STATUS.VALUES.ALARM)
                    {
                        response = SystemConstant.ALARM.MEASURE.TYPE.STATUS.VALUES.ALARM.ToString();
                    }
                    else if (value == (int)SystemConstant.ALARM.MEASURE.COMMON.VALUES.ACKNOWLEDGED)
                    {
                        response = SystemConstant.ALARM.MEASURE.COMMON.VALUES.ACKNOWLEDGED.ToString();
                    }
                }
                // VIRTUAL
                else if (portType.Equals(SystemConstant.MIB.TSDA.TYPE.VIRTUAL.NAME))
                {
                    if (value == (int)SystemConstant.ALARM.MEASURE.COMMON.VALUES.NORMAL)
                    {
                        response = SystemConstant.ALARM.MEASURE.COMMON.VALUES.NORMAL.ToString();
                    }
                    else if (value == (int)SystemConstant.ALARM.MEASURE.TYPE.VIRTUAL.VALUES.WARNING_LOW)
                    {
                        response = SystemConstant.ALARM.MEASURE.TYPE.VIRTUAL.VALUES.WARNING_LOW.ToString();
                    }
                    else if (value == (int)SystemConstant.ALARM.MEASURE.TYPE.VIRTUAL.VALUES.WARNING_HIGH)
                    {
                        response = SystemConstant.ALARM.MEASURE.TYPE.VIRTUAL.VALUES.WARNING_HIGH.ToString();
                    }
                    else if (value == (int)SystemConstant.ALARM.MEASURE.TYPE.VIRTUAL.VALUES.ALARM_LOW)
                    {
                        response = SystemConstant.ALARM.MEASURE.TYPE.VIRTUAL.VALUES.ALARM_LOW.ToString();
                    }
                    else if (value == (int)SystemConstant.ALARM.MEASURE.TYPE.VIRTUAL.VALUES.ALARM_HIGH)
                    {
                        response = SystemConstant.ALARM.MEASURE.TYPE.VIRTUAL.VALUES.ALARM_HIGH.ToString();
                    }
                    else if (value == (int)SystemConstant.ALARM.MEASURE.TYPE.VIRTUAL.VALUES.ALARM)
                    {
                        response = SystemConstant.ALARM.MEASURE.TYPE.VIRTUAL.VALUES.ALARM.ToString();
                    }
                    else if (value == (int)SystemConstant.ALARM.MEASURE.COMMON.VALUES.ACKNOWLEDGED)
                    {
                        response = SystemConstant.ALARM.MEASURE.COMMON.VALUES.ACKNOWLEDGED.ToString();
                    }
                }// PING
                else if (portType.Equals(SystemConstant.MIB.TSDA.TYPE.CUSTOM.PING.NAME))
                {
                    if (value == (int)SystemConstant.ALARM.MEASURE.COMMON.VALUES.NORMAL)
                    {
                        response = SystemConstant.ALARM.MEASURE.COMMON.VALUES.NORMAL.ToString();
                    }
                    else if (value == (int)SystemConstant.ALARM.MEASURE.TYPE.STATUS.VALUES.ALARM)
                    {
                        response = SystemConstant.ALARM.MEASURE.TYPE.STATUS.VALUES.ALARM.ToString();
                    }
                    else if (value == (int)SystemConstant.ALARM.MEASURE.COMMON.VALUES.ACKNOWLEDGED)
                    {
                        response = SystemConstant.ALARM.MEASURE.COMMON.VALUES.ACKNOWLEDGED.ToString();
                    }
                }
                // Command
                else if (portType.Equals(SystemConstant.MIB.TSDA.TYPE.COMMAND.NAME))
                {
                    if (value == (int)SystemConstant.ALARM.MEASURE.COMMON.VALUES.NORMAL)
                    {
                        response = SystemConstant.ALARM.MEASURE.COMMON.VALUES.NORMAL.ToString();
                    }
                    else if (value == (int)SystemConstant.ALARM.MEASURE.TYPE.STATUS.VALUES.ALARM)
                    {
                        response = SystemConstant.ALARM.MEASURE.TYPE.STATUS.VALUES.ALARM.ToString();
                    }
                    else if (value == (int)SystemConstant.ALARM.MEASURE.COMMON.VALUES.ACKNOWLEDGED)
                    {
                        response = SystemConstant.ALARM.MEASURE.COMMON.VALUES.ACKNOWLEDGED.ToString();
                    }
                }
                else if (portType.Equals(SystemConstant.MIB.TSDA.TYPE.CUSTOM.SCRIPT.NAME))
                {
                    if (value == (int)SystemConstant.ALARM.MEASURE.COMMON.VALUES.NORMAL)
                    {
                        response = SystemConstant.ALARM.MEASURE.COMMON.VALUES.NORMAL.ToString();
                    }
                    else if (value == (int)SystemConstant.ALARM.MEASURE.TYPE.STATUS.VALUES.ALARM)
                    {
                        response = SystemConstant.ALARM.MEASURE.TYPE.STATUS.VALUES.ALARM.ToString();
                    }
                    else if (value == (int)SystemConstant.ALARM.MEASURE.COMMON.VALUES.ACKNOWLEDGED)
                    {
                        response = SystemConstant.ALARM.MEASURE.COMMON.VALUES.ACKNOWLEDGED.ToString();
                    }
                }
            }
            catch (Exception exception)
            {
                new LogWriter().Write(exception,
                    TAG, method, BaseService.APPLICATION_TAG);
            }

            return response;
        }


        /// <summary>
        /// Descobre o tipo de alarme de acordo com o índice e tipo de porta
        /// </summary>
        /// <param name="value"></param>
        /// <param name="portType"></param>
        /// <returns></returns>
        public string DiscoverGaugeBehavior(int value, string portType)
        {
            // garante o conteudo caso não entre em nenhuma condição
            string response = SystemConstant.COMPONENT.TYPE.GAUGES.BEHAVIOR.NORMAL;
            string method = "DiscoverGaugeBehavior";
            try
            {
                // METERING
                if (portType.Equals(SystemConstant.MIB.TSDA.TYPE.METERING.NAME))
                {
                    if (value == (int)SystemConstant.ALARM.MEASURE.COMMON.VALUES.NORMAL)
                    {
                        response = SystemConstant.COMPONENT.TYPE.GAUGES.BEHAVIOR.NORMAL;
                    }
                    else if (value == (int)SystemConstant.ALARM.MEASURE.TYPE.METERING.VALUES.WARNING_LOW
                        || value == (int)SystemConstant.ALARM.MEASURE.TYPE.METERING.VALUES.WARNING_HIGH)
                    {
                        response = SystemConstant.COMPONENT.TYPE.GAUGES.BEHAVIOR.WARNING;
                    }
                    else if (value == (int)SystemConstant.ALARM.MEASURE.TYPE.METERING.VALUES.ALARM_LOW
                        || value == (int)SystemConstant.ALARM.MEASURE.TYPE.METERING.VALUES.ALARM_HIGH)
                    {
                        response = SystemConstant.COMPONENT.TYPE.GAUGES.BEHAVIOR.ON_ALARM;
                    }
                    else if (value == (int)SystemConstant.ALARM.MEASURE.COMMON.VALUES.ACKNOWLEDGED)
                    {
                        response = SystemConstant.COMPONENT.TYPE.GAUGES.BEHAVIOR.ACKNOWLEDGED;
                    }
                }
                // STATUS
                else if (portType.Equals(SystemConstant.MIB.TSDA.TYPE.STATUS.NAME))
                {
                    if (value == (int)SystemConstant.ALARM.MEASURE.COMMON.VALUES.NORMAL)
                    {
                        response = SystemConstant.COMPONENT.TYPE.GAUGES.BEHAVIOR.NORMAL;
                    }
                    else if (value == (int)SystemConstant.ALARM.MEASURE.TYPE.STATUS.VALUES.ALARM)
                    {
                        response = SystemConstant.COMPONENT.TYPE.GAUGES.BEHAVIOR.ON_ALARM;
                    }
                    else if (value == (int)SystemConstant.ALARM.MEASURE.COMMON.VALUES.ACKNOWLEDGED)
                    {
                        response = SystemConstant.COMPONENT.TYPE.GAUGES.BEHAVIOR.ACKNOWLEDGED;
                    }
                }
                // VIRTUAL
                else if (portType.Equals(SystemConstant.MIB.TSDA.TYPE.VIRTUAL.NAME))
                {
                    if (value == (int)SystemConstant.ALARM.MEASURE.COMMON.VALUES.NORMAL)
                    {
                        response = SystemConstant.COMPONENT.TYPE.GAUGES.BEHAVIOR.NORMAL;
                    }
                    else if (value == (int)SystemConstant.ALARM.MEASURE.TYPE.VIRTUAL.VALUES.WARNING_LOW
                        || value == (int)SystemConstant.ALARM.MEASURE.TYPE.VIRTUAL.VALUES.WARNING_HIGH)
                    {
                        response = SystemConstant.COMPONENT.TYPE.GAUGES.BEHAVIOR.WARNING;
                    }
                    else if (value == (int)SystemConstant.ALARM.MEASURE.TYPE.VIRTUAL.VALUES.ALARM_LOW
                        || value == (int)SystemConstant.ALARM.MEASURE.TYPE.VIRTUAL.VALUES.ALARM_HIGH
                        || value == (int)SystemConstant.ALARM.MEASURE.TYPE.VIRTUAL.VALUES.ALARM)
                    {
                        response = SystemConstant.COMPONENT.TYPE.GAUGES.BEHAVIOR.ON_ALARM;
                    }
                    else if (value == (int)SystemConstant.ALARM.MEASURE.COMMON.VALUES.ACKNOWLEDGED)
                    {
                        response = SystemConstant.COMPONENT.TYPE.GAUGES.BEHAVIOR.ACKNOWLEDGED;
                    }
                }
            }
            catch (Exception exception)
            {
                new LogWriter().Write(exception,
                    TAG, method, BaseService.APPLICATION_TAG);
            }

            return response;
        }



        /// <summary>
        /// Converte uma string em vetor de bytes considerando a cada DOIS CARACTERES um valor hexadecimal
        /// </summary>
        /// <param name="hexValue"></param>
        /// <returns></returns>
        public byte[] StringToByteArray(string hexValue)
        {
            string method = "StringToByteArray";
            try
            {
                int NumberChars = hexValue.Length / 2;
                byte[] bytes = new byte[NumberChars];
                using (var sr = new StringReader(hexValue))
                {
                    for (int i = 0; i < NumberChars; i++)
                    {
                        try
                        {
                            bytes[i] = Convert.ToByte(new string(new char[2] { (char)sr.Read(), (char)sr.Read() }), 16);
                        }
                        catch (Exception exception)
                        {
                            Console.WriteLine(exception.Message);
                            new LogWriter().Write(exception,
                                TAG, method, BaseService.APPLICATION_TAG);
                        }
                    }
                }
                return bytes;
            }
            catch (Exception exception)
            {
                Console.WriteLine(exception.Message);
                new LogWriter().Write(exception,
                    TAG, method, BaseService.APPLICATION_TAG);
                return null;
            }
        }

        /// <summary>
        /// Clona um mapa a fim de manter o original intacto.
        /// Retorna null caso ocorra falha na operação.
        /// </summary>
        /// <typeparam name="K"></typeparam>
        /// <typeparam name="V"></typeparam>
        /// <param name="obj"></param>
        /// <param name="caller"></param>
        /// <returns></returns>
        public Dictionary<K, V> CloneDictionary<K, V>(Dictionary<K, V> obj, string caller)
        {
            string method = "CloneDictionary";
            try
            {
                string json = JsonConvert.SerializeObject(obj);
                return JsonConvert.DeserializeObject<Dictionary<K, V>>(json);
            }
            catch (Exception exception)
            {
                Console.WriteLine(caller + " -> " + exception.Message);
                new LogWriter().Write(caller + " -> " + exception,
                    TAG, method, BaseService.APPLICATION_TAG);
                return null;
            }
        }

        /// <summary>
        /// Clona um valor específico do mapa a fim de manter o original intacto.
        /// Retorna o proprio object caso ocorra falha na operação.
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <param name="obj"></param>
        /// <param name="caller"></param>
        /// <returns></returns>
        public T CloneEntity<T>(T obj, string caller)
        {
            T responseObject = obj;
            string method = "CloneEntity";
            try
            {
                string json = JsonConvert.SerializeObject(obj);
                if (!string.IsNullOrEmpty(json))
                {
                    responseObject = JsonConvert.DeserializeObject<T>(json);
                }
            }
            catch (Exception exception)
            {
                Console.WriteLine(caller + " -> " + exception.Message);
                new LogWriter().Write(caller + " -> " + exception,
                    TAG, method, BaseService.APPLICATION_TAG);

            }
            return responseObject;
        }

        /// <summary>
        /// Clona um valor específico do mapa a fim de manter o original intacto.
        /// Retorna o proprio object caso ocorra falha na operação.
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <param name="objs"></param>
        /// <returns></returns>
        public List<T> CloneEntities<T>(List<T> objs)
        {
            string method = "CloneEntities";
            try
            {
                string json = JsonConvert.SerializeObject(objs);
                return JsonConvert.DeserializeObject<List<T>>(json);
            }
            catch (Exception exception)
            {
                Console.WriteLine(exception.Message);
                new LogWriter().Write(exception,
                    TAG, method, BaseService.APPLICATION_TAG);
                return null;
            }
        }

        /// <summary>
        /// Clona um valor específico do mapa a fim de manter o original intacto.
        /// Retorna o proprio object caso ocorra falha na operação.
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <param name="objs"></param>
        /// <returns></returns>
        public Queue<T> CloneEntities<T>(Queue<T> objs)
        {
            string method = "CloneEntities";
            try
            {
                string json = JsonConvert.SerializeObject(objs);
                return JsonConvert.DeserializeObject<Queue<T>>(json);
            }
            catch (Exception exception)
            {
                Console.WriteLine(exception.Message);
                new LogWriter().Write(exception,
                    TAG, method, BaseService.APPLICATION_TAG);
                return null;
            }
        }

        /// <summary>
        /// Converte um byte[] em BitmapImage
        /// </summary>
        /// <param name="array"></param>
        /// <returns></returns>
        public BitmapImage ConvertByteArrayToBitmapImage(byte[] array)
        {
            string method = "ConvertByteArrayToBitmapImage";
            try
            {
                using (var ms = new System.IO.MemoryStream(array))
                {
                    var image = new BitmapImage();
                    image.BeginInit();
                    image.CacheOption = BitmapCacheOption.OnLoad;
                    image.StreamSource = ms;
                    image.EndInit();
                    return image;
                }
            }
            catch (Exception exception)
            {
                Console.WriteLine(exception.Message);
                new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG);
                return null;
            }
        }

        /// <summary>
        /// Resize a Bitmap  
        /// </summary>
        /// <param name="image"></param>
        /// <param name="width"></param>
        /// <param name="height"></param>
        /// <returns></returns>
        public byte[] ResizeImageFromByteArray(byte[] image, double width, double height)
        {
            string method = "ResizeImageFromByteArray";
            try
            {
                System.IO.MemoryStream myMemStream = new System.IO.MemoryStream(image);
                System.Drawing.Image fullsizeImage = System.Drawing.Image.FromStream(myMemStream);
                System.Drawing.Image newImage = fullsizeImage.GetThumbnailImage((int)width, (int)height, null, IntPtr.Zero);
                System.IO.MemoryStream myResult = new System.IO.MemoryStream();
                newImage.Save(myResult, System.Drawing.Imaging.ImageFormat.Png);  //Or whatever format you want.
                return myResult.ToArray();  //Returns a new byte array.
            }
            catch (Exception exception)
            {
                Console.WriteLine(exception.Message);
                new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG);
                return null;
            }
        }

        //public byte[] ConvertBitmapImageToByteArray(BitmapImage imageSource)
        //{
        //    Stream stream = imageSource.StreamSource;
        //    byte[] buffer = null;
        //    if (stream != null && stream.Length > 0)
        //    {
        //        using (BinaryReader br = new BinaryReader(stream))
        //        {
        //            buffer = br.ReadBytes((Int32)stream.Length);
        //        }
        //    }
        //    return buffer;
        //}


        /// <summary>
        /// Retorna os oids utilizados para requisições de update nas telas
        /// </summary>
        /// <param name="equipmentId"></param>
        /// <param name="gaugeEquipmentDatas"></param>
        /// <param name="owner"></param>
        /// <returns></returns>
        public string GetOidToUseOnValueUpdateRequests(Guid equipmentId, List<GaugeEquipmentData> gaugeEquipmentDatas, string owner)
        {
            string method = "GetOidToUseOnValueUpdateRequests";
            try
            {
                string result = string.Empty;
                if (owner.Equals(SystemConstant.SYSTEM.TSDA))
                {
                    // oids de valores
                    var equipmentDataValue = gaugeEquipmentDatas
                            .FirstOrDefault(x => x.EquipmentData.OidName == SystemConstant.MIB.TSDA.DEFAULT_COMMON_OID_NAMES.VALUE
                            || x.EquipmentData.OidName == SystemConstant.MIB.TSDA.TYPE.CUSTOM.UT01_SENSOR.DEFAULT_FIELD_NAMES.TEMPERATURE
                            || x.EquipmentData.OidName == SystemConstant.MIB.TSDA.TYPE.CUSTOM.UT01_SENSOR.DEFAULT_FIELD_NAMES.RSSI
                            || x.EquipmentData.OidName == SystemConstant.MIB.TSDA.TYPE.CUSTOM.UT01_SENSOR.DEFAULT_FIELD_NAMES.BATERY
                            || x.EquipmentData.OidName == SystemConstant.MIB.TSDA.TYPE.CUSTOM.UT01_SENSOR.DEFAULT_FIELD_NAMES.HUMIDITY
                            || x.EquipmentData.OidName == SystemConstant.MIB.TSDA.TYPE.CUSTOM.SCRIPT.DEFAULT_FIELD_NAMES.ANSWERED
                            || (x.EquipmentData.PortType.Equals(SystemConstant.MIB.TSDA.TYPE.SYSTEM.NAME) && !x.EquipmentData.OidName.Equals(SystemConstant.MIB.TSDA.DEFAULT_COMMON_OID_NAMES.DESCRIPTION))
                            || (x.EquipmentData.OidName == SystemConstant.MIB.TSDA.TYPE.CUSTOM.PING.DEFAULT_FIELD_NAMES.ALARM_STATE && x.EquipmentData.PortType == SystemConstant.MIB.TSDA.TYPE.CUSTOM.PING.NAME));

                    if (equipmentDataValue != null)
                    {
                        result = equipmentDataValue.EquipmentData.Oid;
                    }
                }
                else if (owner.Equals(SystemConstant.MIB.PROMAX.NAME))
                {
                    var equipmentDataValue = gaugeEquipmentDatas
                                                .FirstOrDefault(x => x.EquipmentData.OidName != SystemConstant.MIB.TSDA.DEFAULT_COMMON_OID_NAMES.DESCRIPTION);

                    if (equipmentDataValue != null)
                    {
                        result = equipmentDataValue.EquipmentData.Oid;
                    }
                }
                else
                {
                    // oids de valores
                    var equipmentDataValue = gaugeEquipmentDatas
                            .FirstOrDefault(x => x.EquipmentData.OidName == SystemConstant.MIB.THIRD_PARTY.DEFAULT_COMMON_OID_NAMES.VALUE);

                    if (equipmentDataValue != null)
                    {
                        result = equipmentDataValue.EquipmentData.Oid;
                    }
                }
                return result;
            }
            catch (Exception exception)
            {
                new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG);
                return string.Empty;
            }
        }

        /// <summary>
        /// Retorna os oids utilizados para requisições de update nas telas
        /// </summary>
        /// <param name="equipmentId"></param>
        /// <param name="gaugeEquipmentDatas"></param>
        /// <returns></returns>
        public string GetOidToUseOnAlarmUpdateRequests(Guid equipmentId, List<GaugeEquipmentData> gaugeEquipmentDatas)
        {
            string result = string.Empty;
            string method = "GetOidToUseOnAlarmUpdateRequests";

            try
            {
                // oids de estado de alarme
                var equipmentDataAlarmState = gaugeEquipmentDatas
                        .FirstOrDefault(x => x.EquipmentData.OidName == SystemConstant.MIB.TSDA.DEFAULT_COMMON_OID_NAMES.ALARM_STATE
                        || x.EquipmentData.OidName == SystemConstant.MIB.THIRD_PARTY.DEFAULT_COMMON_OID_NAMES.ALARM_STATE
                        || x.EquipmentData.OidName == SystemConstant.MIB.TSDA.TYPE.CUSTOM.UT01_SENSOR.DEFAULT_FIELD_NAMES.HUMIDITY_ALARM
                        || x.EquipmentData.OidName == SystemConstant.MIB.TSDA.TYPE.CUSTOM.UT01_SENSOR.DEFAULT_FIELD_NAMES.TEMPERATURE_ALARM);

                if (equipmentDataAlarmState != null)
                {
                    if (equipmentDataAlarmState.EquipmentData.Owner.Equals(SystemConstant.MIB.TSDA.NAME))
                    {
                        result = equipmentDataAlarmState.EquipmentData.Oid;

                    }
                    else
                    {
                        result = equipmentDataAlarmState.EquipmentData.Id.ToString();
                    }
                }
            }
            catch (Exception exception)
            {
                new LogWriter().Write(exception,
                    TAG, method, BaseService.APPLICATION_TAG);
            }

            return result;
        }

        /// <summary>
        /// Retorna o mapa que chegou por parametro(source) preenchido
        /// </summary>
        /// <param name="source"></param>
        /// <param name="map"></param>
        /// <returns></returns>
        public Dictionary<Guid, List<string>> BuildOidListToUseOnPollingRequests(Dictionary<Guid, List<string>> source, Dictionary<Guid, Dictionary<string, string>> map)
        {
            string method = "BuildOidListToUseOnPollingRequests";
            try
            {
                if (map != null && map.Count != 0)
                {
                    // para cada equipamento em aux
                    foreach (var auxEquipment in map)
                    {
                        try
                        {
                            // para cada oid em aux
                            foreach (var auxOid in auxEquipment.Value)
                            {
                                try
                                {
                                    // MAPA DE OIDS DE VALOR
                                    // se a chave do equipamento não existe no mapa principal, cria instância de lista no mesmo
                                    // e aloca o oid.
                                    if (!source.ContainsKey(auxEquipment.Key))
                                    {
                                        source.Add(auxEquipment.Key, new List<string>());
                                    }
                                    source[auxEquipment.Key].Add(auxOid.Value);
                                }
                                catch (Exception exception)
                                {
                                    new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG);
                                }
                            }
                        }
                        catch (Exception exception)
                        {
                            new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG);
                        }
                    }
                }
            }
            catch (Exception exception)
            {
                new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG);
            }

            return source;
        }

        /// <summary>
        /// Obtem o encoding
        /// </summary>
        /// <param name="filename"></param>
        /// <returns></returns>
        public Encoding GetEncoding(string filename)
        {
            string method = "GetEncoding";
            try
            {
                // Read the BOM
                var bom = new byte[4];
                using (var file = new FileStream(filename, FileMode.Open, FileAccess.Read))
                {
                    file.Read(bom, 0, 4);
                }

                // Analyze the BOM
                if (bom[0] == 0x2b && bom[1] == 0x2f && bom[2] == 0x76) return Encoding.UTF7;
                if (bom[0] == 0xef && bom[1] == 0xbb && bom[2] == 0xbf) return Encoding.UTF8;
                if (bom[0] == 0xff && bom[1] == 0xfe) return Encoding.Unicode; //UTF-16LE
                if (bom[0] == 0xfe && bom[1] == 0xff) return Encoding.BigEndianUnicode; //UTF-16BE
                if (bom[0] == 0 && bom[1] == 0 && bom[2] == 0xfe && bom[3] == 0xff) return Encoding.UTF32;
                return Encoding.ASCII;
            }
            catch (Exception exception)
            {
                new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG);
                return Encoding.ASCII;
            }
        }

        /// <summary>
        /// Descobre o nome deste OID de sistema
        /// </summary>
        /// <param name="oidName"></param>
        /// <returns></returns>
        public string DiscoverNameOfSystemProperty(string oidName, string owner)
        {
            string method = "DiscoverNameOfSystemProperty";
            try
            {
                if (owner.Equals(SystemConstant.MIB.TSDA.NAME))
                {
                    switch (oidName)
                    {
                        case SystemConstant.MIB.TSDA.TYPE.SYSTEM.DEFAULT_FIELD_NAMES.CHIP_01_RSSI:
                            return "Chip 01 RSSI";
                        case SystemConstant.MIB.TSDA.TYPE.SYSTEM.DEFAULT_FIELD_NAMES.CHIP_02_RSSI:
                            return "Chip 02 RSSI";
                        case SystemConstant.MIB.TSDA.TYPE.SYSTEM.DEFAULT_FIELD_NAMES.DATABASE:
                            return "Database Version";
                        case SystemConstant.MIB.TSDA.TYPE.SYSTEM.DEFAULT_FIELD_NAMES.DATE:
                            return "Current Date";
                        case SystemConstant.MIB.TSDA.TYPE.SYSTEM.DEFAULT_FIELD_NAMES.DESCRIPTION:
                            return "Description";
                        case SystemConstant.MIB.TSDA.TYPE.SYSTEM.DEFAULT_FIELD_NAMES.GATEWAY_ETHERNET_01:
                            return "Gateway";
                        case SystemConstant.MIB.TSDA.TYPE.SYSTEM.DEFAULT_FIELD_NAMES.IP_ETHERNET_01:
                            return "IP Ethernet 01";
                        case SystemConstant.MIB.TSDA.TYPE.SYSTEM.DEFAULT_FIELD_NAMES.IP_ETHERNET_02:
                            return "IP Ethernet 02";
                        case SystemConstant.MIB.TSDA.TYPE.SYSTEM.DEFAULT_FIELD_NAMES.IP_GSM:
                            return "IP GSM";
                        case SystemConstant.MIB.TSDA.TYPE.SYSTEM.DEFAULT_FIELD_NAMES.LOAD:
                            return "Load Average";
                        case SystemConstant.MIB.TSDA.TYPE.SYSTEM.DEFAULT_FIELD_NAMES.MAC:
                            return "MAC";
                        case SystemConstant.MIB.TSDA.TYPE.SYSTEM.DEFAULT_FIELD_NAMES.PW_SUPPLY:
                            return "Power Supply";
                        case SystemConstant.MIB.TSDA.TYPE.SYSTEM.DEFAULT_FIELD_NAMES.RAMDISK:
                            return "Ramdisk";
                        case SystemConstant.MIB.TSDA.TYPE.SYSTEM.DEFAULT_FIELD_NAMES.REBOOT:
                            return "Reboot";
                        case SystemConstant.MIB.TSDA.TYPE.SYSTEM.DEFAULT_FIELD_NAMES.SERIAL:
                            return "Serial";
                        case SystemConstant.MIB.TSDA.TYPE.SYSTEM.DEFAULT_FIELD_NAMES.TEMPERATURE:
                            return "Temperature";
                        case SystemConstant.MIB.TSDA.TYPE.SYSTEM.DEFAULT_FIELD_NAMES.TIME:
                            return "Current Time";
                        case SystemConstant.MIB.TSDA.TYPE.SYSTEM.DEFAULT_FIELD_NAMES.UPTIME:
                            return "System Up Time";
                        case SystemConstant.MIB.TSDA.TYPE.SYSTEM.DEFAULT_FIELD_NAMES.WAN_CHIP_01:
                            return "Chip 01 Enable";
                        case SystemConstant.MIB.TSDA.TYPE.SYSTEM.DEFAULT_FIELD_NAMES.WAN_CHIP_02:
                            return "Chip 02 Enable";
                        case SystemConstant.MIB.TSDA.TYPE.SYSTEM.DEFAULT_FIELD_NAMES.WAN_ETHERNET:
                            return "Ethernet Enable";
                        case SystemConstant.MIB.TSDA.TYPE.SYSTEM.DEFAULT_FIELD_NAMES.WAN_VPN:
                            return "VPN";
                        default:
                            return oidName;

                    }
                }
                else
                {
                    if (owner.Equals(SystemConstant.MIB.PROMAX.NAME))
                    {
                        switch (oidName)
                        {
                            case SystemConstant.MIB.PROMAX.PROWATCH.SYSTEM.DEFAULT_FIELD_NAMES.LNB:
                                return "LNB";
                            case SystemConstant.MIB.PROMAX.PROWATCH.SYSTEM.DEFAULT_FIELD_NAMES.TEMPERATURE:
                                return "Temperature";
                            case SystemConstant.MIB.PROMAX.PROWATCH.SYSTEM.DEFAULT_FIELD_NAMES.STORAGE_1:
                                return "Storage 01";
                            case SystemConstant.MIB.PROMAX.PROWATCH.SYSTEM.DEFAULT_FIELD_NAMES.STORAGE_2:
                                return "Storage 02";
                            case SystemConstant.MIB.PROMAX.PROWATCH.SYSTEM.DEFAULT_FIELD_NAMES.STORAGE_3:
                                return "Storage 03";
                            default:
                                return oidName;
                        }
                    }
                    else
                    {
                        return oidName;

                    }
                }
            }
            catch (Exception exception)
            {
                new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG);
                return oidName;
            }
        }

        /// <summary>
        /// converte o uptime vindo da flex
        /// </summary>
        /// <param name="timeToConvert"></param>
        /// <returns></returns>
        public string ConvertFlexUpTime(string timeToConvert)
        {
            string method = "ConvertFlexUpTime";
            try
            {
                int i;
                string result = string.Empty;
                var aux = timeToConvert.Split(',');
                foreach (var item in aux)
                {
                    var splitedUnit = item.Split(' ');
                    foreach (var unit in splitedUnit)
                    {
                        try
                        {
                            if (Int32.TryParse(unit, out i))
                            {
                                result += unit;
                            }
                            if (unit.Equals("days"))
                            {
                                result += "d ";
                            }
                            else if (unit.Equals("hours"))
                            {
                                result += "h ";
                            }
                            else if (unit.Equals("minutes"))
                            {
                                result += "m";
                            }
                        }
                        catch (Exception exception)
                        {
                            new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG);
                        }
                    }
                }

                return result;
            }
            catch (Exception exception)
            {
                new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG);
                return timeToConvert;
            }
        }


        /// <summary>
        /// ConvertFlexCurrentDate
        /// </summary>
        /// <param name="value"></param>
        /// <returns></returns>
        public string ConvertFlexCurrentDate(string value)
        {
            string method = "ConvertFlexCurrentDate";
            try
            {
                return value.Split('-')[1];
            }
            catch (Exception exception)
            {
                new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG);
                return value;
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
        // ~UtilService() {
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

        /// <summary>
        /// DiscoverSnoozeOid
        /// </summary>
        /// <param name="value"></param>
        /// <returns></returns>
        public string DiscoverSnoozeOid(EquipmentData equipmentData)
        {
            string method = "DiscoverSnoozeOid";
            try
            {
                string oid = string.Empty;

                if (equipmentData.PortType.Equals(SystemConstant.MIB.TSDA.TYPE.METERING.NAME))
                {
                    oid = SystemConstant.MIB.TSDA.TYPE.METERING.DEFAULT_OID.SNOOZE + equipmentData.PortNumber;
                }
                else if (equipmentData.PortType.Equals(SystemConstant.MIB.TSDA.TYPE.STATUS.NAME))
                {
                    oid = SystemConstant.MIB.TSDA.TYPE.STATUS.DEFAULT_OID.SNOOZE + equipmentData.PortNumber;
                }
                else if (equipmentData.PortType.Equals(SystemConstant.MIB.TSDA.TYPE.COMMAND.NAME))
                {
                    oid = SystemConstant.MIB.TSDA.TYPE.COMMAND.DEFAULT_OID.SNOOZE + equipmentData.PortNumber;
                }
                else if (equipmentData.PortType.Equals(SystemConstant.MIB.TSDA.TYPE.VIRTUAL.NAME))
                {
                    oid = SystemConstant.MIB.TSDA.TYPE.VIRTUAL.DEFAULT_OID.SNOOZE + equipmentData.PortNumber;
                }
                else if (equipmentData.PortType.Equals(SystemConstant.MIB.TSDA.TYPE.VIRTUAL_OUTPUT.NAME))
                {
                    oid = SystemConstant.MIB.TSDA.TYPE.VIRTUAL_OUTPUT.DEFAULT_OID.SNOOZE + equipmentData.PortNumber;
                }
                else if (equipmentData.PortType.Equals(SystemConstant.MIB.TSDA.TYPE.CUSTOM.MODBUS_RTU.NAME))
                {
                    oid = SystemConstant.MIB.TSDA.TYPE.CUSTOM.MODBUS_RTU.DEFAULT_OID.SNOOZE + equipmentData.PortNumber;
                }
                else if (equipmentData.PortType.Equals(SystemConstant.MIB.TSDA.TYPE.CUSTOM.MODBUS_TCP.NAME))
                {
                    oid = SystemConstant.MIB.TSDA.TYPE.CUSTOM.MODBUS_TCP.DEFAULT_OID.SNOOZE + equipmentData.PortNumber;
                }
                else if (equipmentData.PortType.Equals(SystemConstant.MIB.TSDA.TYPE.CUSTOM.SCRIPT.NAME))
                {
                    oid = SystemConstant.MIB.TSDA.TYPE.CUSTOM.SCRIPT.DEFAULT_OID.SNOOZE + equipmentData.PortNumber;
                }
                else if (equipmentData.PortType.Equals(SystemConstant.MIB.TSDA.TYPE.CUSTOM.UT01_SENSOR.NAME))
                {
                    oid = SystemConstant.MIB.TSDA.TYPE.CUSTOM.UT01_SENSOR.DEFAULT_OID.SNOOZE + equipmentData.PortNumber;
                }
                else if (equipmentData.PortType.Equals(SystemConstant.MIB.TSDA.TYPE.CUSTOM.PING.NAME))
                {
                    oid = SystemConstant.MIB.TSDA.TYPE.CUSTOM.PING.DEFAULT_OID.SNOOZE + equipmentData.PortNumber;
                }

                return oid;
            }
            catch (Exception exception)
            {
                new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG);
                return null;
            }
        }






        /// <summary>
        /// Trata o estado de alarme da medida de terceiro
        /// 
        /// 
        /// </summary>
        /// <param name="gaugeType"></param>
        /// <param name="currentValue"></param>
        /// <param name="currentSerializedAlarmState"></param>
        /// <param name="rangeMin"></param>
        /// <param name="rangeMax"></param>
        /// <param name="alarmMin"></param>
        /// <param name="alarmMax"></param>
        /// <param name="hysteresis"></param>
        /// <param name="alarmList"></param>
        /// <returns></returns>
        public SystemConstant.MIB.THIRD_PARTY.ALARM.DATA DiscoverThirdPartyAlarmState(string gaugeType, string currentValue, string currentSerializedAlarmState, double rangeMin = 0, double rangeMax = 100, double alarmMin = 0, double alarmMax = 100, double hysteresis = 1, List<string> alarmList = null)
        {
            string method = "DiscoverThirdPartyAlarmState";
            SystemConstant.MIB.THIRD_PARTY.ALARM.DATA currentAlarmState = new SystemConstant.MIB.THIRD_PARTY.ALARM.DATA().GetDefault();
            try
            {
                if (!string.IsNullOrEmpty(currentSerializedAlarmState))
                {
                    try
                    {
                        currentAlarmState = JsonConvert.DeserializeObject<SystemConstant.MIB.THIRD_PARTY.ALARM.DATA>(currentSerializedAlarmState);
                    }
                    catch (Exception exception)
                    {
                        Console.WriteLine(exception);
                        currentAlarmState = new SystemConstant.MIB.THIRD_PARTY.ALARM.DATA().GetDefault();
                    }
                }

                if (currentValue == null || currentValue.Contains("NoSuch"))
                {
                    return currentAlarmState;
                }

                if (gaugeType.Equals(SystemConstant.MIB.TSDA.TYPE.METERING.NAME))
                {
                    //double parsedValue = double.Parse(currentValue.Replace(',', '.'), CultureInfo.InvariantCulture);
                    //double parsedValue = double.Parse(currentValue.Replace(",", "."), CultureInfo.InvariantCulture);

                    double parsedValue = 0;
                    if (double.TryParse(currentValue.Replace(",", "."), NumberStyles.Any, CultureInfo.InvariantCulture, out parsedValue))
                    {
                        double alarmMinActivateAlarm = alarmMin - hysteresis;
                        if (alarmMinActivateAlarm < rangeMin) alarmMinActivateAlarm = rangeMin;
                        double alarmMinDeactivateAlarm = alarmMin + hysteresis;
                        if (alarmMinDeactivateAlarm > rangeMax) alarmMinDeactivateAlarm = rangeMax;

                        double alarmMaxActivateAlarm = alarmMax + hysteresis;
                        if (alarmMaxActivateAlarm > rangeMax) alarmMaxActivateAlarm = rangeMax;
                        double alarmMaxDeactivateAlarm = alarmMax - hysteresis;
                        if (alarmMaxDeactivateAlarm < rangeMin) alarmMaxDeactivateAlarm = rangeMin;

                        if (parsedValue < alarmMinActivateAlarm)
                        {
                            if (!currentAlarmState.CurrentState.Equals(SystemConstant.ALARM.MEASURE.COMMON.VALUES.ACKNOWLEDGED.ToString()))
                            {
                                currentAlarmState.CurrentState = SystemConstant.ALARM.MEASURE.TYPE.METERING.VALUES.ALARM_LOW.ToString();
                            }
                        }
                        else if (parsedValue > alarmMaxActivateAlarm)
                        {
                            if (!currentAlarmState.CurrentState.Equals(SystemConstant.ALARM.MEASURE.COMMON.VALUES.ACKNOWLEDGED.ToString()))
                            {
                                currentAlarmState.CurrentState = SystemConstant.ALARM.MEASURE.TYPE.METERING.VALUES.ALARM_HIGH.ToString();
                            }
                        }
                        else
                        {
                            if (parsedValue > alarmMinDeactivateAlarm && parsedValue < alarmMaxDeactivateAlarm)
                            {
                                currentAlarmState.CurrentState = SystemConstant.ALARM.MEASURE.COMMON.VALUES.NORMAL.ToString();
                            }
                        }
                    }
                }
                else
                {
                    if (alarmList != null)
                    {
                        bool isAlarmed = false;
                        foreach (var item in alarmList)
                        {
                            try
                            {
                                if (item.Equals(currentValue))
                                {
                                    isAlarmed = true;
                                    break;
                                }
                            }
                            catch (Exception exception)
                            {
                                Console.WriteLine(exception.Message);
                            }
                        }

                        if (isAlarmed)
                        {
                            if (!currentAlarmState.CurrentState.Equals(SystemConstant.ALARM.MEASURE.COMMON.VALUES.ACKNOWLEDGED.ToString()))
                            {
                                currentAlarmState.CurrentState = SystemConstant.ALARM.MEASURE.TYPE.STATUS.VALUES.ALARM.ToString();
                            }
                        }
                        else
                        {
                            currentAlarmState.CurrentState = SystemConstant.ALARM.MEASURE.COMMON.VALUES.NORMAL.ToString();
                        }
                    }
                    else
                    {
                        currentAlarmState.CurrentState = SystemConstant.ALARM.MEASURE.COMMON.VALUES.NORMAL.ToString();
                    }
                }

                return currentAlarmState;
            }
            catch (Exception exception)
            {
                new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG);
                return currentAlarmState;
            }
        }

        /// <summary>
        /// Traduz o valor da medida de terceiros
        /// </summary>
        /// <param name="value"></param>
        /// <param name="translate"></param>
        /// <returns></returns>
        public string TranslateTPMeasure(string value, string translate)
        {
            string method = "TranslateTPMeasure";
            try
            {
                if (string.IsNullOrEmpty(translate))
                {
                    return value;
                }

                if (string.IsNullOrEmpty(value))
                {
                    return value;
                }

                Dictionary<string, string> translateMap = JsonConvert.DeserializeObject<Dictionary<string, string>>(translate);

                if (translateMap != null && translateMap.Count != 0)
                {
                    string translatedValue = string.Empty;
                    if (translateMap.TryGetValue(value, out translatedValue))
                    {
                        return translatedValue;
                    }
                }

                return value;
            }
            catch (Exception exception)
            {
                new LogWriter().Write(exception, TAG, method, BaseService.APPLICATION_TAG);
                Console.WriteLine(exception.Message);
                return string.Empty;
            }
        }

        /// <summary>
        /// aplica o fator em medias de terceiros
        /// </summary>
        /// <param name="factor"></param>
        /// <param name="current"></param>
        /// <returns></returns>
        public double ApplyFactor(string factor, string current)
        {
            double currentValue = SystemConstant.FACTOR.ERROR;
            try
            {
                double tryParseCurrentValue = 0;
                if (string.IsNullOrEmpty(current))
                {
                    return currentValue;
                }

                if (string.IsNullOrEmpty(factor))
                {
                    double.TryParse(current.Replace(",", "."), NumberStyles.Any, CultureInfo.InvariantCulture, out tryParseCurrentValue);
                    return tryParseCurrentValue;
                }

                //currentValue = double.Parse(current, CultureInfo.InvariantCulture);
                if (double.TryParse(current.Replace(",", "."), NumberStyles.Any, CultureInfo.InvariantCulture, out currentValue))
                {
                    string op = factor.FirstOrDefault().ToString(CultureInfo.InvariantCulture);
                    string factorValueString = factor.Remove(0, 1);
                    factorValueString = factorValueString.Replace(",", ".");

                    double factorValue = 0;

                    //factorValue = double.Parse(factorValueString, CultureInfo.InvariantCulture);
                    if (double.TryParse(factorValueString.Replace(",", "."), NumberStyles.Any, CultureInfo.InvariantCulture, out factorValue))
                    {
                        switch (op)
                        {
                            case "+":
                                return currentValue + factorValue;

                            case "-":
                                return currentValue - factorValue;

                            case "*":
                                return currentValue * factorValue;

                            case "/":
                                return currentValue / factorValue;

                            default:
                                return currentValue;
                        }
                    }
                    else
                    {
                        return currentValue;
                    }
                }
                else
                {
                    return currentValue;
                }
            }
            catch (Exception exception)
            {
                Console.WriteLine(exception.Message);
            }
            return currentValue;
        }


        /// <summary>
        /// 
        /// </summary>
        /// <param name="digitalAlarm"></param>
        /// <returns></returns>
        public List<string> GetTPDigitalAlarmList(string alarms)
        {
            try
            {
                List<string> AlarmList = new List<string>();

                if (alarms.Contains(';'))
                {
                    string[] alarmStates = alarms.Split(';');

                    foreach (var item in alarmStates)
                    {
                        try
                        {
                            if (!string.IsNullOrEmpty(item))
                            {
                                AlarmList.Add(item);

                            }
                        }
                        catch (Exception exception)
                        {
                            Console.WriteLine(exception.Message);
                        }
                    }
                }
                else
                {
                    AlarmList.Add(alarms);
                }

                return AlarmList;
            }
            catch (Exception exception)
            {
                Console.WriteLine(exception.Message);
                return null;
            }
        }

        public string GetStatusofPromax(GaugeEquipmentData eqdtAlarm)
        {
            try
            {
                switch (eqdtAlarm.EquipmentData.OidName)
                {
                    case SystemConstant.MIB.PROMAX.PROWATCH.SYSTEM.DEFAULT_FIELD_NAMES.LNB:
                        if (eqdtAlarm.EquipmentData.Value.Equals("1"))
                        {
                            return SystemConstant.MIB.PROMAX.PROWATCH.SYSTEM.STATE.LNB.SHORT.ToString();
                        }
                        else
                            return SystemConstant.MIB.PROMAX.PROWATCH.SYSTEM.STATE.LNB.OK.ToString();

                    case SystemConstant.MIB.PROMAX.PROWATCH.SYSTEM.DEFAULT_FIELD_NAMES.TEMPERATURE:
                        if (eqdtAlarm.EquipmentData.Value.Equals("1"))
                        {
                            return SystemConstant.MIB.PROMAX.PROWATCH.SYSTEM.STATE.TEMPERATURE.HIGH.ToString();
                        }
                        else
                            return SystemConstant.MIB.PROMAX.PROWATCH.SYSTEM.STATE.TEMPERATURE.OK.ToString();
                    case SystemConstant.MIB.PROMAX.PROWATCH.SYSTEM.DEFAULT_FIELD_NAMES.STORAGE_1:
                        if (eqdtAlarm.EquipmentData.Value.Equals("1"))
                        {
                            return SystemConstant.MIB.PROMAX.PROWATCH.SYSTEM.STATE.STORAGE.FULL.ToString();
                        }
                        else
                            return SystemConstant.MIB.PROMAX.PROWATCH.SYSTEM.STATE.STORAGE.OK.ToString();
                    case SystemConstant.MIB.PROMAX.PROWATCH.SYSTEM.DEFAULT_FIELD_NAMES.STORAGE_2:
                        if (eqdtAlarm.EquipmentData.Value.Equals("1"))
                        {
                            return SystemConstant.MIB.PROMAX.PROWATCH.SYSTEM.STATE.STORAGE.FULL.ToString();
                        }
                        else
                            return SystemConstant.MIB.PROMAX.PROWATCH.SYSTEM.STATE.STORAGE.OK.ToString();
                    case SystemConstant.MIB.PROMAX.PROWATCH.SYSTEM.DEFAULT_FIELD_NAMES.STORAGE_3:
                        if (eqdtAlarm.EquipmentData.Value.Equals("1"))
                        {
                            return SystemConstant.MIB.PROMAX.PROWATCH.SYSTEM.STATE.STORAGE.FULL.ToString();
                        }
                        else
                            return SystemConstant.MIB.PROMAX.PROWATCH.SYSTEM.STATE.STORAGE.OK.ToString();
                    default:
                        return eqdtAlarm.EquipmentData.Value;
                }

            }
            catch (Exception exception)
            {
                Console.WriteLine(exception.Message);

                return eqdtAlarm.EquipmentData.Value;
            }
        }

        public static List<EquipmentData> BuildPromaxMonitoringOids(Guid equipmentId)
        {
            try
            {
                List<EquipmentData> oids = new List<EquipmentData>();




                oids.Add(new EquipmentData()
                {
                    Oid = SystemConstant.MIB.PROMAX.PROWATCH.MONITORING.DEFAULT_OID.PROFILE_SELECTED,
                    PortType = SystemConstant.MIB.PROMAX.PROWATCH.MONITORING.NAME,
                    EquipmentId = equipmentId,

                });

                //oids.Add(new EquipmentData()
                //{
                //    Oid = SystemConstant.MIB.PROMAX.PROWATCH.MONITORING.DEFAULT_OID.CHANNEL_FREQUENCY_LIST_BASE,
                //    PortType = SystemConstant.MIB.PROMAX.PROWATCH.MONITORING.NAME,
                //    EquipmentId = equipmentId,

                //});

                //oids.Add(new EquipmentData()
                //{
                //    Oid = SystemConstant.MIB.PROMAX.PROWATCH.MONITORING.DEFAULT_OID.PROFILE_LIST_BASE,
                //    PortType = SystemConstant.MIB.PROMAX.PROWATCH.MONITORING.NAME,
                //    EquipmentId = equipmentId,

                //});

                oids.Add(new EquipmentData()
                {
                    Oid = SystemConstant.MIB.PROMAX.PROWATCH.MONITORING.DEFAULT_OID.CHANNEL_SIGNALTYPE_LIST_BASE,
                    PortType = SystemConstant.MIB.PROMAX.PROWATCH.MONITORING.NAME,
                    EquipmentId = equipmentId,

                });

                //oids.Add(new EquipmentData()
                //{
                //    Oid = SystemConstant.MIB.PROMAX.PROWATCH.MONITORING.DEFAULT_OID.MEASURE_ALARM_LIST_BASE,
                //    PortType = SystemConstant.MIB.PROMAX.PROWATCH.MONITORING.NAME,
                //    EquipmentId = equipmentId,

                //});

                //oids.Add(new EquipmentData()
                //{
                //    Oid = SystemConstant.MIB.PROMAX.PROWATCH.MONITORING.DEFAULT_OID.MEASURE_EXPONENT_LIST_BASE,
                //    PortType = SystemConstant.MIB.PROMAX.PROWATCH.MONITORING.NAME,
                //    EquipmentId = equipmentId,

                //});

                oids.Add(new EquipmentData()
                {
                    Oid = SystemConstant.MIB.PROMAX.PROWATCH.MONITORING.DEFAULT_OID.MEASURE_VALUE_LIST_BASE,
                    PortType = SystemConstant.MIB.PROMAX.PROWATCH.MONITORING.NAME,
                    EquipmentId = equipmentId,

                });

                //oids.Add(new EquipmentData()
                //{
                //    Oid = SystemConstant.MIB.PROMAX.PROWATCH.MONITORING.DEFAULT_OID.MEASURE_UNIT_LIST,
                //    PortType = SystemConstant.MIB.PROMAX.PROWATCH.MONITORING.NAME,
                //    EquipmentId = equipmentId,

                //});

                oids.Add(new EquipmentData()
                {
                    Oid = SystemConstant.MIB.PROMAX.PROWATCH.MONITORING.DEFAULT_OID.MEASURE_TYPE_LIST_BASE,
                    PortType = SystemConstant.MIB.PROMAX.PROWATCH.MONITORING.NAME,
                    EquipmentId = equipmentId,

                });

                oids.Add(new EquipmentData()
                {
                    Oid = SystemConstant.MIB.PROMAX.PROWATCH.MONITORING.DEFAULT_OID.CHANNEL_LIST_BASE,
                    PortType = SystemConstant.MIB.PROMAX.PROWATCH.MONITORING.NAME,
                    EquipmentId = equipmentId,

                });


                return oids;
            }
            catch (Exception exception)
            {
                return null;
            }
        }


        public static List<EquipmentData> BuildPromaxTuningOids(Guid equipmentId)
        {
            try
            {
                List<EquipmentData> oids = new List<EquipmentData>();




                for (int i = 0; i < 20; i++) {
                    oids.Add(new EquipmentData()
                    {
                        Oid = SystemConstant.MIB.PROMAX.PROWATCH.TUNING.DEFAULT_OID.ALARM_STATE_BASE+i,
                        PortType = SystemConstant.MIB.PROMAX.PROWATCH.TUNING.NAME,
                        EquipmentId = equipmentId,

                    });

                    oids.Add(new EquipmentData()
                    {
                        Oid = SystemConstant.MIB.PROMAX.PROWATCH.TUNING.DEFAULT_OID.EXPONENT_BASE + i,
                        PortType = SystemConstant.MIB.PROMAX.PROWATCH.TUNING.NAME,
                        EquipmentId = equipmentId,

                    });

                    oids.Add(new EquipmentData()
                    {
                        Oid = SystemConstant.MIB.PROMAX.PROWATCH.TUNING.DEFAULT_OID.UNIT_BASE + i,
                        PortType = SystemConstant.MIB.PROMAX.PROWATCH.TUNING.NAME,
                        EquipmentId = equipmentId,

                    });

                    oids.Add(new EquipmentData()
                    {
                        Oid = SystemConstant.MIB.PROMAX.PROWATCH.TUNING.DEFAULT_OID.VALUE_BASE + i,
                        PortType = SystemConstant.MIB.PROMAX.PROWATCH.TUNING.NAME,
                        EquipmentId = equipmentId,

                    });
                }


                oids.Add(new EquipmentData()
                {
                    Oid = SystemConstant.MIB.PROMAX.PROWATCH.TUNING.DEFAULT_OID.CHANNEL_NAME,
                    PortType = SystemConstant.MIB.PROMAX.PROWATCH.TUNING.NAME,
                    EquipmentId = equipmentId,

                });

                oids.Add(new EquipmentData()
                {
                    Oid = SystemConstant.MIB.PROMAX.PROWATCH.TUNING.DEFAULT_OID.SIGNAL_TYPE,
                    PortType = SystemConstant.MIB.PROMAX.PROWATCH.TUNING.NAME,
                    EquipmentId = equipmentId,

                });

                oids.Add(new EquipmentData()
                {
                    Oid = SystemConstant.MIB.PROMAX.PROWATCH.TUNING.DEFAULT_OID.FREQUENCY,
                    PortType = SystemConstant.MIB.PROMAX.PROWATCH.TUNING.NAME,
                    EquipmentId = equipmentId,

                });

                return oids;
            }
            catch (Exception exception)
            {
                return null;
            }
        }
        //public static  string SqliteEncoding(string message)
        //{
        //    try
        //    {
        //        string msg = "";

        //        Encoding iso = Encoding.GetEncoding("ISO-8859-1");
        //       // Encoding utf8 = Encoding.GetEncoding("CP1252");
        //        byte[] utfBytes = utf8.GetBytes(message);
        //        byte[] isoBytes = Encoding.Convert(utf8, iso, utfBytes);
        //         msg = iso.GetString(isoBytes);
        //        return msg;

        //    }
        //    catch (Exception exception)
        //    {
        //        Console.WriteLine(exception.Message);
        //        return string.Empty;
        //    }
        //}

    }


}

