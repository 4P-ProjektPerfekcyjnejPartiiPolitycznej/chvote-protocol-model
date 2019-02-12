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
import com.google.common.collect.ImmutableMap;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * This model class contains all the data necessary to verify the validity of an election, tally excepted.
 */
@SuppressWarnings("unused")
public class ElectionVerificationData {
  private final PublicParameters                  publicParameters;
  private final ElectionSetForVerification        electionSet;
  private final List<BigInteger>                  primes;
  private final List<BigInteger>                  generators;
  private final Map<Integer, EncryptionPublicKey> publicKeyParts;
  private final EncryptionPublicKey               electionOfficerPublicKey;
  private final Map<Integer, List<Point>>         publicCredentials;
  private final Map<Integer, BallotAndQuery>      ballots;
  private final Map<Integer, Confirmation>        confirmations;
  private final Map<Integer, List<Encryption>>    shuffles;
  private final Map<Integer, ShuffleProof>        shuffleProofs;
  private final Map<Integer, Decryptions>         partialDecryptions;
  private final Map<Integer, DecryptionProof>     decryptionProofs;

  @JsonCreator
  public ElectionVerificationData(
      @JsonProperty("publicParameters") PublicParameters publicParameters,
      @JsonProperty("electionSet") ElectionSetForVerification electionSet,
      @JsonProperty("primes") List<BigInteger> primes,
      @JsonProperty("generators") List<BigInteger> generators,
      @JsonProperty("publicKeyParts") Map<Integer, EncryptionPublicKey> publicKeyParts,
      @JsonProperty("electionOfficerPublicKey") EncryptionPublicKey electionOfficerPublicKey,
      @JsonProperty("publicCredentials") Map<Integer, List<Point>> publicCredentials,
      @JsonProperty("ballots") Map<Integer, BallotAndQuery> ballots,
      @JsonProperty("confirmations") Map<Integer, Confirmation> confirmations,
      @JsonProperty("shuffles") Map<Integer, List<Encryption>> shuffles,
      @JsonProperty("shuffleProofs") Map<Integer, ShuffleProof> shuffleProofs,
      @JsonProperty("partialDecryptions") Map<Integer, Decryptions> partialDecryptions,
      @JsonProperty("decryptionProofs") Map<Integer, DecryptionProof> decryptionProofs) {
    this.publicParameters = publicParameters;
    this.electionSet = electionSet;
    this.primes = ImmutableList.copyOf(primes);
    this.generators = ImmutableList.copyOf(generators);
    this.publicKeyParts = ImmutableMap.copyOf(publicKeyParts);
    this.electionOfficerPublicKey = electionOfficerPublicKey;
    this.publicCredentials = ImmutableMap.copyOf(publicCredentials);
    this.ballots = ImmutableMap.copyOf(ballots);
    this.confirmations = ImmutableMap.copyOf(confirmations);
    this.shuffles = shuffles.entrySet().stream()
                            .collect(ImmutableMap
                                         .toImmutableMap(Map.Entry::getKey, e -> ImmutableList.copyOf(e.getValue())));
    this.shuffleProofs = ImmutableMap.copyOf(shuffleProofs);
    this.partialDecryptions = ImmutableMap.copyOf(partialDecryptions);
    this.decryptionProofs = ImmutableMap.copyOf(decryptionProofs);
  }

  public PublicParameters getPublicParameters() {
    return publicParameters;
  }

  public ElectionSetForVerification getElectionSet() {
    return electionSet;
  }

  public List<BigInteger> getPrimes() {
    return primes;
  }

  public List<BigInteger> getGenerators() {
    return generators;
  }

  public Map<Integer, EncryptionPublicKey> getPublicKeyParts() {
    return publicKeyParts;
  }

  public EncryptionPublicKey getElectionOfficerPublicKey() {
    return electionOfficerPublicKey;
  }

  public Map<Integer, List<Point>> getPublicCredentials() {
    return publicCredentials;
  }

  public Map<Integer, BallotAndQuery> getBallots() {
    return ballots;
  }

  public Map<Integer, Confirmation> getConfirmations() {
    return confirmations;
  }

  public Map<Integer, List<Encryption>> getShuffles() {
    return shuffles;
  }

  public Map<Integer, ShuffleProof> getShuffleProofs() {
    return shuffleProofs;
  }

  public Map<Integer, Decryptions> getPartialDecryptions() {
    return partialDecryptions;
  }

  public Map<Integer, DecryptionProof> getDecryptionProofs() {
    return decryptionProofs;
  }
}
