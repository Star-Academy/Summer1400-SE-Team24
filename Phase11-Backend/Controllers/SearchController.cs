using System.Collections.Generic;
using System.Linq;
using Microsoft.AspNetCore.Mvc;
using InvertedIndex.Data.Services;
using InvertedIndex.Logic;

namespace InvertedIndex.Controllers
{
    [ApiController]
    public class SearchController : ControllerBase
    {
        private readonly IDocRepository _docHandler;
        private readonly SearchEngine _engine;
        private readonly QueryParser _parser;

        public SearchController(IDocRepository docHandler,
            SearchEngine engine, QueryParser parser)
        {
            _docHandler = docHandler;
            _engine = engine;
            _parser = parser;
        }

        [HttpGet]
        [Route("/{q}")]
        public IEnumerable<string> Get(string q)
        {
            return _engine.Search(_parser.ParseQuery(q))
                .Select(d => d.DocID);
        }
    }
}
