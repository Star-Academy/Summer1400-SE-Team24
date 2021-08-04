
using Xunit;
using InvertedIndex.Search;

namespace InvertedIndex.Test
{
    public class QueryParserTest
    {
        [Fact]
        public void parseQueryTest() {
            var parser = new QueryParser();
            string query = "-exclude ordinary +union";
            var keywords = parser.parseQuery(query);

            Assert.Equal("ordinary", keywords[0].GetWord());
            Assert.Equal("union", keywords[1].GetWord());
            Assert.Equal("exclude", keywords[2].GetWord());
        }
    }
}