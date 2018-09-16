package org.bitbucket.bodand.nhscore.data.hentai.factory

import org.bitbucket.bodand.nhscore.data.hentai.HentaiCharacter

import scala.language.existentials


class HentaiCharacterFactory extends HentaiDataFactory[HentaiCharacter] {
  override def construct(name: String,
                         amount: Int)
                        (implicit id: Int): HentaiCharacter = {
    new HentaiCharacter(name, amount)
  }
}
