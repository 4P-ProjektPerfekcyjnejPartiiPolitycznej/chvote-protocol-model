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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Model class for the voter
 */
@SuppressWarnings("unused")
public final class Voter implements Serializable {
  private final int                     voterId;
  private final String businessId;
  private final CountingCircle          countingCircle;
  private final String                  printingAuthorityName;
  private final List<DomainOfInfluence> allowedDomainsOfInfluence;

  @JsonCreator
  public Voter(
      @JsonProperty("voterId") int voterId,
      @JsonProperty("businessId") String businessId,
      @JsonProperty("countingCircle") CountingCircle countingCircle,
      @JsonProperty("printingAuthorityName") String printingAuthorityName,
      @JsonProperty("allowedDomainsOfInfluence") DomainOfInfluence... allowedDomainsOfInfluence) {
    this.voterId = voterId;
    this.businessId = businessId;
    this.countingCircle = countingCircle;
    this.printingAuthorityName = printingAuthorityName;
    this.allowedDomainsOfInfluence = ImmutableList.copyOf(allowedDomainsOfInfluence);
  }

  public int getVoterId() {
    return voterId;
  }

  public String getBusinessId() {
    return businessId;
  }

  public CountingCircle getCountingCircle() {
    return countingCircle;
  }

  public String getPrintingAuthorityName() {
    return printingAuthorityName;
  }

  public List<DomainOfInfluence> getAllowedDomainsOfInfluence() {
    return allowedDomainsOfInfluence;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Voter voter = (Voter) o;
    return voterId == voter.voterId &&
           Objects.equals(businessId, voter.businessId) &&
           Objects.equals(countingCircle, voter.countingCircle) &&
           Objects.equals(printingAuthorityName, voter.printingAuthorityName) &&
           Objects.equals(allowedDomainsOfInfluence, voter.allowedDomainsOfInfluence);
  }

  @Override
  public int hashCode() {
    return Objects.hash(voterId, businessId, countingCircle, printingAuthorityName, allowedDomainsOfInfluence);
  }
}
