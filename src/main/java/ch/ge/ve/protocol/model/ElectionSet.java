/*-------------------------------------------------------------------------------------------------
 - #%L                                                                                            -
 - protocol-model                                                                                 -
 - %%                                                                                             -
 - Copyright (C) 2016 - 2018 République et Canton de Genève                                       -
 - %%                                                                                             -
 - This program is free software: you can redistribute it and/or modify                           -
 - it under the terms of the GNU Affero General Public License as published by                    -
 - the Free Software Foundation, either version 3 of the License, or                              -
 - (at your option) any later version.                                                            -
 -                                                                                                -
 - This program is distributed in the hope that it will be useful,                                -
 - but WITHOUT ANY WARRANTY; without even the implied warranty of                                 -
 - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the                                   -
 - GNU General Public License for more details.                                                   -
 -                                                                                                -
 - You should have received a copy of the GNU Affero General Public License                       -
 - along with this program. If not, see <http://www.gnu.org/licenses/>.                           -
 - #L%                                                                                            -
 -------------------------------------------------------------------------------------------------*/

package ch.ge.ve.protocol.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This is the generic definition of an election set.
 * This model class contains the definition of a set of elections
 */
@SuppressWarnings("unused")
public abstract class ElectionSet<P extends PrintingAuthority> implements Serializable {
  private final List<Election>  elections;
  private final List<Candidate> candidates;
  private final List<P>         printingAuthorities;
  private final long            voterCount;
  private final int             countingCircleCount;

  @JsonCreator
  public ElectionSet(@JsonProperty("elections") List<Election> elections,
                     @JsonProperty("candidates") List<Candidate> candidates,
                     @JsonProperty("printingAuthorities") List<P> printingAuthorities,
                     @JsonProperty("voterCount") long voterCount,
                     @JsonProperty("countingCircleCount") int countingCircleCount) {
    Preconditions.checkArgument(
        candidates.size() == elections.stream().mapToInt(Election::getNumberOfCandidates).sum());
    this.elections = ImmutableList.copyOf(elections);
    this.candidates = ImmutableList.copyOf(candidates);
    this.printingAuthorities = ImmutableList.copyOf(printingAuthorities);
    this.voterCount = voterCount;
    this.countingCircleCount = countingCircleCount;
  }

  public boolean isEligible(Voter voter, Election election) {
    return voter.getAllowedDomainsOfInfluence().contains(election.getApplicableDomainOfInfluence());
  }

  @JsonIgnore
  public List<Integer> getBold_n() {
    return elections.stream().map(Election::getNumberOfCandidates).collect(Collectors.toList());
  }

  @JsonIgnore
  public int getK() {
    return elections.stream().mapToInt(Election::getNumberOfSelections).sum();
  }

  public List<Election> getElections() {
    return elections;
  }

  public List<Candidate> getCandidates() {
    return candidates;
  }

  public List<P> getPrintingAuthorities() {
    return printingAuthorities;
  }

  public long getVoterCount() {
    return voterCount;
  }

  public int getCountingCircleCount() {
    return countingCircleCount;
  }
}
