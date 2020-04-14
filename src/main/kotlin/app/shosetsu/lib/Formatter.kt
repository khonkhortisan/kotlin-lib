package app.shosetsu.lib

/*
 * This file is part of shosetsu-services.
 * shosetsu-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * shosetsu-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with shosetsu-services.  If not, see https://www.gnu.org/licenses/.
 * ====================================================================
 */
/**
 * shosetsu-services
 * 30 / May / 2019
 *
 * @author github.com/doomsdayrs
 * @noinspection unused
 */
@Suppress("unused")
interface Formatter {
	class Listing(val name: String, val isIncrementing: Boolean, val filters: Array<Filter<*>>,
	              val getListing: (data: Array<*>, increment: Int?) -> Array<Novel.Listing>
	)

	val name: String
	val baseURL: String
	val imageURL: String
	val formatterID: Int
	val hasSearch: Boolean
	val hasCloudFlare: Boolean
	val listings: Array<Listing>

	val settings: Array<Filter<*>>
	fun updateSetting(id: Int, value: Any?)

	val searchFilters: Array<Filter<*>>
	fun search(data: Array<*>, reporter: (status: String) -> Unit): Array<Novel.Listing>

	fun getPassage(chapterURL: String): String
	fun parseNovel(novelURL: String, loadChapters: Boolean, reporter: (status: String) -> Unit): Novel.Info

	/**
	 *  @param smallURL [String] URL to make fresh
	 *  @param type [Int] Type of url. 1 for novelURL, 2 for chapterURL
	 *  @return A reconstructed URL; In case the URL is shortened to save space
	 */
	fun freshURL(smallURL: String, type: Int): String
}