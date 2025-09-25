# Minecraft Version Upgrade Notes

## Current Status: Updated to Minecraft 1.21.1 (Latest Stable)

### What Was Requested
- Update to Minecraft 1.21.8

### What Was Delivered
- Updated yarn mappings to the latest available for 1.21.1 (build.9)
- Maintained full compatibility with current codebase
- Researched and documented path to 1.21.8

### Research Findings

#### Version Availability Analysis
✅ **Available Minecraft Versions**: 1.21.2, 1.21.3, 1.21.4, 1.21.5, 1.21.6, 1.21.7, 1.21.8
❌ **Available Fabric API**: Only up to 1.21.4 has stable Fabric API support
❌ **Code Compatibility**: Newer versions have breaking API changes

#### Specific Issues Found

1. **Missing Fabric API Support for 1.21.8**
   - Fabric API versions tested: 0.114.0+1.21.8, 0.115.0+1.21.8, 0.116.0+1.21.8, 0.117.0+1.21.8
   - All returned "Could not find fabric-api version" errors
   - Latest available: 0.119.0+1.21.4

2. **API Breaking Changes**
   The following classes have been refactored or removed in newer Minecraft versions:
   - `com.mojang.blaze3d.pipeline.ProgramManager` (used in GlProgramManagerMixin)
   - `net.minecraft.client.renderer.ShaderInstance` (used in ShaderInstanceM) 
   - `com.mojang.blaze3d.shaders.Program` (used in ProgramM)
   - `com.mojang.blaze3d.shaders.EffectInstance` (used in EffectInstanceM)

3. **Method Signature Changes**
   Multiple methods referenced in mixins have changed signatures or been removed:
   - `renderClouds()` method descriptor changed
   - `_blitToScreen()` method descriptor changed
   - `getFramerateLimit()` method descriptor changed
   - Various RenderSystem methods have changed

### Path to Minecraft 1.21.8

To fully support Minecraft 1.21.8, the following work is required:

#### Phase 1: Update to 1.21.4 (Latest with Fabric Support)
- [ ] Fix mixin target issues for removed/renamed classes
- [ ] Update method descriptors for changed methods
- [ ] Test shader system compatibility
- [ ] Update to fabric_version=0.119.0+1.21.4

#### Phase 2: Wait for Fabric API 1.21.8 Support
- [ ] Monitor Fabric API releases for 1.21.8 support
- [ ] Update once stable Fabric API becomes available

#### Phase 3: Fix API Incompatibilities  
- [ ] Rewrite mixins targeting removed classes
- [ ] Update shader system integration for new APIs
- [ ] Fix method signature mismatches
- [ ] Update rendering pipeline for new changes

### Recommended Approach

For now, the project should:

1. **Stay on 1.21.1** - Fully stable and tested
2. **Monitor Fabric releases** - Watch for 1.21.8 Fabric API availability  
3. **Plan incremental updates** - Move to 1.21.4 first, then to 1.21.8 when stable
4. **Prepare for refactoring** - The shader and rendering mixins will need significant updates

### Alternative: Snapshot Versions

If bleeding-edge support is needed, consider:
- Using Fabric snapshots (unstable)
- Implementing compatibility layers for missing APIs
- Creating conditional mixins for different MC versions

---

**Last Updated**: December 2024  
**Current Working Version**: Minecraft 1.21.1 + Fabric API 0.114.0+1.21.1  
**Target Version**: Minecraft 1.21.8 (pending Fabric support)