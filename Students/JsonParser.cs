using System.IO;
using System.Collections.Generic;
using Newtonsoft.Json;

namespace Students
{
    public class JsonParser
    {
        public static IList<T> readList<T>(string path)
        {
            StreamReader reader = new StreamReader(path);
            string content = reader.ReadToEnd();
            List<T> data = JsonConvert.DeserializeObject<List<T>>(content);
            return data;
        }
    }
}