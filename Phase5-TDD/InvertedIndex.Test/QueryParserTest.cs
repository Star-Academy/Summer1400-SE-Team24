using System;
using System.Collections.Generic;
using Xunit;
using InvertedIndex.Search;
using InvertedIndex.Keywords;

namespace InvertedIndex.Test
{
    public class QueryParserTest : IDisposable
    {
        const string TEST_QUERY = "-exclude ordinary +union";
        IList<Keyword> _keywords;

        public QueryParserTest()
        {
            var parser = new QueryParser();
            _keywords = parser.ParseQuery(TEST_QUERY);
        }

        [Fact]
        public void ParseQueryTest1()
        {
            Assert.Equal("ordinary", _keywords[0].GetWord());
        }

        [Fact]
        public void ParseQueryTest2()
        {
            Assert.Equal("union", _keywords[1].GetWord());
        }

        [Fact]
        public void ParseQueryTest3()
        {
            Assert.Equal("exclude", _keywords[2].GetWord());
        }

        public void Dispose()
        {
            GC.Collect();
        }
    }
}