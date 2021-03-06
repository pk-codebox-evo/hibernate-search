/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.search.query.dsl.impl;

import org.apache.lucene.util.ToStringUtils;
import org.hibernate.search.analyzer.impl.RemoteAnalyzerReference;

/**
 * A match query interpreted remotely, thus based on the remote analyzers.
 *
 * @author Guillaume Smet
 */
public class RemoteMatchQuery extends AbstractRemoteQueryWithAnalyzer {

	private String field;

	private String searchTerms;

	private int maxEditDistance = 0;

	public static class Builder {
		private String field;

		private String searchTerms;

		private int maxEditDistance = 0;

		private RemoteAnalyzerReference analyzerReference;

		public Builder field(String field) {
			this.field = field;
			return this;
		}

		public Builder searchTerms(String terms) {
			this.searchTerms = terms;
			return this;
		}

		public Builder maxEditDistance(int maxEditDistance) {
			this.maxEditDistance = maxEditDistance;
			return this;
		}

		public Builder analyzerReference(RemoteAnalyzerReference analyzerReference) {
			this.analyzerReference = analyzerReference;
			return this;
		}

		public RemoteMatchQuery build() {
			return new RemoteMatchQuery( field, searchTerms, maxEditDistance, analyzerReference );
		}
	}

	private RemoteMatchQuery(String field, String terms, int maxEditDistance,
			RemoteAnalyzerReference analyzerReference) {
		super( analyzerReference );
		this.field = field;
		this.maxEditDistance = maxEditDistance;
		this.searchTerms = terms;
	}

	public String getField() {
		return field;
	}

	public String getSearchTerms() {
		return searchTerms;
	}

	public int getMaxEditDistance() {
		return maxEditDistance;
	}

	@Override
	public String toString(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append( getClass().getSimpleName() ).append( "<" );
		sb.append( field ).append( ":" );
		sb.append( searchTerms );
		if ( maxEditDistance != 0 ) {
			sb.append( "~" ).append( maxEditDistance );
		}
		sb.append( ToStringUtils.boost( getBoost() ) );
		if ( getAnalyzerReference() != null ) {
			sb.append( ",analyzer=" ).append( getAnalyzerReference() );
		}
		sb.append( ">" );
		return sb.toString();
	}

}
