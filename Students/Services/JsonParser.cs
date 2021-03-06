using System.IO;
using System.Collections.Generic;
using Newtonsoft.Json;

namespace Students.Services
{
    public class JsonParser
    {
        public static IList<T> ReadList<T>(string path)
        {
            using (var reader = new StreamReader(path))
            {
                string content = reader.ReadToEnd();
                return JsonConvert.DeserializeObject<List<T>>(content);
            }
        }
    }
}