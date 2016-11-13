package com.lothrazar.cyclicmagic.module;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import amerifrance.guideapi.api.IPage;
import amerifrance.guideapi.api.impl.Book;
import amerifrance.guideapi.api.impl.abstraction.CategoryAbstract;
import amerifrance.guideapi.api.impl.abstraction.EntryAbstract;
import amerifrance.guideapi.category.CategoryItemStack;
import amerifrance.guideapi.entry.EntryItemStack;
import amerifrance.guideapi.page.PageFurnaceRecipe;
import amerifrance.guideapi.page.PageIRecipe;
import amerifrance.guideapi.page.PageText;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ItemGuidebookModule extends BaseModule {
  public static Book myGuide;
  @Override
  public void onPreInit() {
    Map<ResourceLocation, EntryAbstract> entries = new HashMap<ResourceLocation, EntryAbstract>();

    List<IPage> pages1 = new ArrayList<IPage>();
    pages1.add(new PageText("This is a page in my guide!"));
    entries.put(new ResourceLocation("example", "entry_one"), new EntryItemStack(pages1, "Entry One", new ItemStack(Blocks.BEACON)));

    List<IPage> pages2 = new ArrayList<IPage>();
    pages2.add(new PageIRecipe(new ShapedOreRecipe(Items.DIAMOND_SWORD, "D", "D", "S", 'D', Items.DIAMOND, 'S', Items.STICK)));
    pages2.add(new PageFurnaceRecipe("oreGold"));
    entries.put(new ResourceLocation("example", "entry_two"), new EntryItemStack(pages2, "Entry Two", new ItemStack(Items.DIAMOND_SWORD)));

    List<CategoryAbstract> categories = new ArrayList<CategoryAbstract>();
    categories.add(new CategoryItemStack(entries, "My Category", new ItemStack(Blocks.COMMAND_BLOCK)));

    myGuide = new Book();
    myGuide.setTitle("My Guide");
    myGuide.setDisplayName("My Guide");
    myGuide.setAuthor("ExampleDude");
    myGuide.setColor(Color.CYAN);
    myGuide.setCategoryList(categories);
    myGuide.setRegistryName("FirstGuide");

    GameRegistry.register(myGuide);
  }
}
